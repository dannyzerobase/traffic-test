package com.traffictest.board.controller;

import com.traffictest.board.service.InfinityCommentService;
import com.traffictest.entity.InfinityComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/infinity-comments")
@RequiredArgsConstructor
public class InfinityCommentController {

    private final InfinityCommentService infinityCommentInitService;

    @GetMapping
    public List<InfinityComment> findAll() {
        return infinityCommentInitService.findAll();
    }

    @GetMapping("/without-content")
    public String getWithoutContent() {
        log.info("call");
        infinityCommentInitService.findAll();
        return "Success";
    }

    @GetMapping("/init")
    public long init(@RequestParam(defaultValue = "50000") Integer count) {
        long startTime = System.currentTimeMillis();
        infinityCommentInitService.init(count);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    @GetMapping("/enhanced-init")
    public long enhancedInit(@RequestParam(defaultValue = "50000") Integer count) {
        long startTime = System.currentTimeMillis();
        infinityCommentInitService.enhancedInit(count);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
