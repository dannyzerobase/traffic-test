package com.traffictest.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BoardSummaryDto(
        Long id,
        String subject,
        List<CommentDto> comments,
        List<HashTagDto> hashTags,
        String writer,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long commentCount,
        Long likeCount
) {
}

