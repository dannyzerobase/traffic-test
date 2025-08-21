package com.traffictest.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/async")
public class AsyncController {

    private final WebClient webClient = WebClient.builder().baseUrl("http://statistics-app:8191").build();

    @GetMapping("/future")
    public CompletableFuture<String> future() {
        return CompletableFuture.supplyAsync(() ->
                webClient.get().uri("/delay").retrieve().bodyToMono(String.class).block());
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        return webClient.get().uri("/delay").retrieve().bodyToMono(String.class);
    }

    @GetMapping("/flux")
    public Flux<String> flux() {

        // 호출을 1000개 id 50개씩 묶어서 보낸다거나
        return Flux.range(1, 10).flatMap(it ->
                webClient.get().uri("/delay").retrieve().bodyToMono(String.class));
    }

}
