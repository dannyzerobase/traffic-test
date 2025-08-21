package com.traffictest.board.service;

import com.traffictest.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardInitService {

    List<String> hashSlots = List.of("HASH1", "HASH2", "HASH3", "HASH4", "HASH5");
    List<String> subjects = List.of("hello", "world", "goodbye");

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public void init(long initCount) {
        int subjectCounter = 0;
        // 1. make a base data;
        for (int boardCnt = 0; boardCnt < initCount; boardCnt++) {
            User user = userRepository.save(User.builder()
                    .username("user" + boardCnt)
                    .build());

            List<HashTag> hashTags = new ArrayList<>();
            for (int hashTagCnt = 0; hashTagCnt < 5; hashTagCnt++) {
                hashTags.add(HashTag.builder()
                        .name(hashSlots.get(hashTagCnt))
                        .build());
            }

            Board board = Board.builder()
                    .subject(subjects.get(subjectCounter++ % 3))
                    .content("content" + boardCnt)
                    .writer(user)
                    .hashTags(hashTags)
                    .build();

            // Set the board for each hashTag
            for (HashTag hashTag : hashTags) {
                hashTag.setBoard(board);
            }

            boardRepository.save(board);

            List<Comment> comments = new ArrayList<>();
            for (int commentCnt = 0; commentCnt < 5; commentCnt++) {
                comments.add(Comment.builder()
                        .content("comment" + boardCnt + "_" + commentCnt)
                        .board(board)
                        .writer(user)
                        .build());
            }
            commentRepository.saveAll(comments);
            for (int commentCnt = 0; commentCnt < 5; commentCnt++) {
                CommentLike commentLike = CommentLike.builder()
                        .comment(comments.get(commentCnt))
                        .likedBy(user)
                        .build();
                commentLikeRepository.save(commentLike);
            }

        }


    }
}
