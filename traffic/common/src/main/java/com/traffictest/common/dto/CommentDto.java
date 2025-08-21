package com.traffictest.common.dto;

public record CommentDto(
        Long id,
        String content,
        Long writerId,
        String writerName
) {
}
