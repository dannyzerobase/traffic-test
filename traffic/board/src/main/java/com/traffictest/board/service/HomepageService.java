package com.traffictest.board.service;

import com.traffictest.common.dto.PageItems;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class HomepageService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://statistics-app:8191").build();

    // 2분
    @Cacheable(cacheNames = "redis-home",key = "#userId",cacheManager = "redisCacheManager")
    public List<PageItems> getWithCache(Long userId){
        if(userId == Long.MIN_VALUE){
            return webClient.get()
                    .uri("/landing")
                    .retrieve().bodyToMono(new ParameterizedTypeReference<List<PageItems>>() {
                    }).block();
        }
        return webClient.get()
                .uri("/landing?userId="+userId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<PageItems>>() {
                }).block();
    }
}
