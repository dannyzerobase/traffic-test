package com.traffictest.entity;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select bl from BoardLike  bl where bl.board.id = :boardId and bl.writerId = :writerId")
    Optional<BoardLike> findWithLock(long boardId, long writerId);

    Optional<BoardLike> findByBoard_IdAndWriterId(long boardId, long writerId);

    int countByBoard_Id(long boardId);
}
