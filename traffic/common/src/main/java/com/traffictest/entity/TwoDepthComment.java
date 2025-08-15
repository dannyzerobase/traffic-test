package com.traffictest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TwoDepthComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "two depth reply id")
    private List<TwoDepthReply> replyList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer id", nullable = false)
    private User writer;
}
