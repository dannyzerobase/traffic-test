package com.traffictest.board.controller;

import com.traffictest.board.service.TwoDepthReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/two-depth")
@RequiredArgsConstructor
public class TwoDepthReplyController {
    private final TwoDepthReplyService twoDepthReplyService;

    @GetMapping("/without-content")
    public String withoutContent() {
        twoDepthReplyService.findAll();
        return "Hello";
    }

    @GetMapping("/init")
    public Long init(@RequestParam(defaultValue = "50000") int count) {
        long startTime = System.currentTimeMillis();
        twoDepthReplyService.init(count);
        long end = System.currentTimeMillis();

        return end - startTime;
    }
}
