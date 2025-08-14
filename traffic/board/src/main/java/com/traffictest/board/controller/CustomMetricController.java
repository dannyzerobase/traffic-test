package com.traffictest.board.controller;

import com.traffictest.board.metrics.MetricsCollector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class CustomMetricController {

    private final MetricsCollector metricsCollector;

    private final WebClient webClient = WebClient.builder().baseUrl("http://statistics-app:8191").build();

    @GetMapping
    public int tester(){

        int result = 0;
        for(int i=0;i<10;i++){
            try {
                webClient.get().uri("/metrics")
                        .retrieve().bodyToMono(String.class)
                        .block();
                result ++;
                metricsCollector.count("CUSTOM_METRIC","tag","success");
            }catch (RuntimeException e){
                metricsCollector.count("CUSTOM_METRIC","tag","fail");
                log.warn("exception : ",e);
            }
        }
        return result;
    }
}
