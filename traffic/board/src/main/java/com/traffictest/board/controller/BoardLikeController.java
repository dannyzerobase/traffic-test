package com.traffictest.board.controller;


import com.traffictest.board.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @GetMapping("/toggle")
    public void switchBoardLikeState(@RequestParam Long boardId, @RequestParam Long userId) {
        boardLikeService.switchLikeState(boardId, userId);
    }

    @GetMapping("/toggle-transaction")
    public void switchBoardLikeStateTransaction(@RequestParam Long boardId, @RequestParam Long userId) {
        boardLikeService.switchLikeStateWithTransaction(boardId, userId);
        boardLikeService.switchLikeStateWithTransaction(boardId, userId);
    }
}