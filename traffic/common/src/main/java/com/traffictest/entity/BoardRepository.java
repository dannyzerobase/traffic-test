package com.traffictest.entity;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Board b where b.id = :boardId")
    Optional<Board> findWithLock(Long boardId);

    @Modifying
    @Query("UPDATE Board b set b.likeCount = b.likeCount+1 where b.id = :BoardId")
    int increaseLikeCount(Long boardId);

    @Modifying
    @Query("UPDATE Board b set b.likeCount = b.likeCount-1 where b.id = :BoardId")
    int decreaseLikeCount(Long boardId);
}
