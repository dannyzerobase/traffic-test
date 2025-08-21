package com.traffictest.entity;

import com.traffictest.common.dto.BoardSummaryDto;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardSummaryDto> findBoardSummaryDtos(String hashTag, String subjectLike);
}
