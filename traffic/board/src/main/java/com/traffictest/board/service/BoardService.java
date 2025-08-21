package com.traffictest.board.service;


import com.traffictest.entity.Board;
import com.traffictest.entity.BoardRepository;
import com.traffictest.common.dto.BoardSummaryDto;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final Faker faker = new Faker();

    public List<Board> getBoards() {
        return null;
    }

    public List<BoardSummaryDto> findByHashTagAndSubject(String hashTag, String subjectLike) {
        return boardRepository.findBoardSummaryDtos(hashTag, subjectLike);
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public void putRandomBoard(int count) {
        for (int i = 0; i < count; i++) {
            boardRepository.save(Board.builder()
                    .subject(faker.book().title())
                    .content((faker.book().publisher()))
                    .build());
        }
    }
//
//    public int categoryCount(Category category){
//        return boardRepository.findAllByCategory(category).size();
//    }
//    public long categoryCountEnhancement(Category category){
//        return boardRepository.countAllByCategory(category);
//    }
}
