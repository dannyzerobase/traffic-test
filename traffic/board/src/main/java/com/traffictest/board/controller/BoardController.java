package com.traffictest.board.controller;

import com.traffictest.board.service.BoardInitService;
import com.traffictest.board.service.BoardService;
import com.traffictest.common.dto.BoardSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardInitService boardInitService;

    // for index test
    @GetMapping
    public List<BoardSummaryDto> getBoard(@RequestParam String hashTag, @RequestParam String subjectLike) {
        return boardService.findByHashTagAndSubject(hashTag, subjectLike);
    }
    // 5000개 기준 15분 정도 시간 소요
    //
    @GetMapping("init-index")
    public long initIndex(@RequestParam int count) {
        long startTime = System.currentTimeMillis();
        boardInitService.init(count);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
