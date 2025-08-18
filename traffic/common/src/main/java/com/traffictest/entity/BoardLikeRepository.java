package com.traffictest.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findByBoard_IdAndWriterId(long boardId, long writerId);

}