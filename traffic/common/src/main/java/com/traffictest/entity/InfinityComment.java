package com.traffictest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(indexes = {
        @Index(name = "infinity_comment_createdAt_idx", columnList = "createdAt")
})
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfinityComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private InfinityComment parent;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;
}
// 1~100,