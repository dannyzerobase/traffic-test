package com.traffictest.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CacheObject {
    private Long id;
    private String key;
    private LocalDateTime lastAccessedAt;
}
