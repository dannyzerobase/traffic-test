package com.traffictest.board.service;


import com.traffictest.entity.Board;
import com.traffictest.entity.BoardRepository;
import com.traffictest.common.dto.BoardSummaryDto;
import lombok.RequiredArgsConstructor;
import io.micrometer.core.instrument.MeterRegistry;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MeterRegistry meterRegistry;
    private final WebClient.Builder webClientBuilder;
    @Value("${statistics.base-url}")
    private String statisticsBaseUrl;
    private final Faker faker = new Faker();
    private static final String STATISTICS_CALL_METRIC = "board_statistics_api_calls";

    public List<Board> getBoards() {
        return null;
    }

    public List<BoardSummaryDto> findByHashTagAndSubject(String hashTag, String subjectLike) {
        return boardRepository.findBoardSummaryDtos(hashTag, subjectLike);
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public int callStatisticsHealth(int count) {
        WebClient webClient = webClientBuilder.baseUrl(statisticsBaseUrl).build();
        int successCount = 0;

        for (int i = 0; i < count; i++) {
            try {
                webClient.get()
                        .uri("/health")
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                meterRegistry.counter(STATISTICS_CALL_METRIC, "result", "success").increment();
                successCount++;
            } catch (Exception ex) {
                meterRegistry.counter(STATISTICS_CALL_METRIC, "result", "error").increment();
            }
        }

        return successCount;
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
