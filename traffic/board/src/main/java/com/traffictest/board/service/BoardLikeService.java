package com.traffictest.board.service;

import com.traffictest.entity.Board;
import com.traffictest.entity.BoardLike;
import com.traffictest.entity.BoardLikeRepository;
import com.traffictest.entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardLikeService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    public void switchLikeState(Long boardId, Long userId) {
        Optional<BoardLike> boardLikeOptional =
                boardLikeRepository.findByBoard_IdAndWriterId(boardId, userId);
        // 좋아요 -;
        if (boardLikeOptional.isPresent()) {
            BoardLike boardLike = boardLikeOptional.get();
            Board board = boardLike.getBoard();
            board.setLikeCount(board.getLikeCount() - 1);
            boardRepository.save(board);
            boardLikeRepository.delete(boardLike);
            return;
        }
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.setLikeCount(board.getLikeCount() + 1);
            boardRepository.save(board);
            boardLikeRepository.save(BoardLike.builder()
                    .board(board).writerId(userId).build());
        } else {
            log.error("Not found boardId");
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void switchLikeStateWithTransactionWithLock(Long boardId, Long userId) {
        Optional<Board> boardOptional = boardRepository.findWithLock(boardId);
        if (boardOptional.isEmpty()) {
            log.error("Not found boardId");
            return;
        }
        Board board = boardOptional.get();

        Optional<BoardLike> boardLikeOptional =
                boardLikeRepository.findWithLock(boardId, userId);
        // 좋아요 -;
        if (boardLikeOptional.isPresent()) {
            BoardLike boardLike = boardLikeOptional.get();
            board.setLikeCount(board.getLikeCount() - 1);
            boardRepository.save(board);
            boardRepository.decreaseLikeCount(boardId);
            boardLikeRepository.delete(boardLike);
        } else {
            boardRepository.increaseLikeCount(boardId);
            board.setLikeCount(board.getLikeCount() + 1);

            boardLikeRepository.save(BoardLike.builder()
                    .board(board).writerId(userId).build());
        }
    }

}
