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

    @GetMapping("/toggle-transaction-lock")
    public void switchBoardLikeStateTransactionLock(@RequestParam Long boardId, @RequestParam Long userId) {
        boardLikeService.switchLikeStateWithTransactionWithLock(boardId, userId);
    }

    @GetMapping("/toggle-publish")
    public void togglePublish(@RequestParam Long boardId, @RequestParam Long userId){
        boardLikeService.switchLikeStateWithPublish(boardId,userId);
    }
    @GetMapping("/toggle-async-publish")
    public void togglePublishAsync(@RequestParam Long boardId, @RequestParam Long userId){
        boardLikeService.switchLikeStateWithAsyncPublish(boardId,userId);
    }
}
