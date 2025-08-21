package com.traffictest.entity;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.traffictest.common.dto.BoardSummaryDto;
import com.traffictest.common.dto.CommentDto;
import com.traffictest.common.dto.HashTagDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardSummaryDto> findBoardSummaryDtos(String hashTag, String subjectLike) {
        QBoard qBoard = QBoard.board;
        QHashTag qHashTag = QHashTag.hashTag;
        QComment qComment = QComment.comment;
        QCommentLike qCommentLike = QCommentLike.commentLike;
        QUser qCommentWriter = new QUser("commentWriter");
        QUser qBoardWriter = new QUser("boardWriter");

        return queryFactory.select(
                Projections.constructor(BoardSummaryDto.class,
                        qBoard.id,      // long
                        qBoard.subject, // string
                        Projections.list(Projections.constructor(CommentDto.class, // commentDtos
                                qComment.id,
                                qComment.content,
                                qComment.writer.id.as("writerId"),
                                qCommentWriter.username.as("writerName"))),
                        Projections.list(Projections.constructor(HashTagDto.class,
                                qHashTag.name)),
//                        qBoard.hashTags,        // hashTags
                        qBoardWriter.username.as("writer"),
                        qBoard.createdAt,
                        qBoard.updatedAt,
                        qComment.countDistinct().as("commentCount"),
                        qCommentLike.count().as("likeCount")))
                .from(qBoard)
                .join(qBoard.hashTags, qHashTag)
                .join(qBoard.comments, qComment)
                .join(qComment.commentLikes, qCommentLike)
                .join(qComment.writer, qCommentWriter)
                .join(qBoard.writer, qBoardWriter)
                .where(qHashTag.name.eq(hashTag)
                        .and(qBoard.subject.like("%" + subjectLike + "%")))
                .groupBy(qBoard.id)
                .fetch();
    }
}
