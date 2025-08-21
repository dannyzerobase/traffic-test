package com.traffictest.statistics.subscriber;

import com.traffictest.entity.BoardLikeRepository;
import com.traffictest.entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardLikeSubscriber {
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;


    public void onMessage(String message){
        Long boardId = Long.valueOf(message);
        int currCount = boardLikeRepository.countByBoard_Id(boardId);
        boardRepository.updateLikeCount(boardId,currCount);
    }
}
