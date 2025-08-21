package com.traffictest.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfinityCommentRepository extends JpaRepository<InfinityComment, Long> {
// 정렬 전후
    @Query(value =
            """
       WITH RECURSIVE comment_hierarchy AS (
                        SELECT id, parent_id, content, writer_id, created_at, updated_at, 1 AS depth
                        FROM infinity_comment
                        WHERE parent_id IS NULL
                    
                        UNION ALL
                    
                        SELECT c.id, c.parent_id, c.content, c.writer_id, c.created_at, c.updated_at, ch.depth + 1
                        FROM infinity_comment c
                        INNER JOIN comment_hierarchy ch ON c.parent_id = ch.id
                    )
                    SELECT *
                    FROM comment_hierarchy
                    ORDER BY depth ASC, created_at ASC;
                    """
            , nativeQuery = true)
    List<InfinityComment> findCommentHierarchy();
}
