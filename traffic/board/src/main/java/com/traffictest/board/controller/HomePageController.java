package com.traffictest.board.controller;

import com.traffictest.board.service.HomepageService;
import com.traffictest.common.dto.PageItems;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomePageController {

    private final HomepageService homepageService;

    @GetMapping
    public List<PageItems> getHome(@RequestParam(required = false) Long userId){
        // 데이터를 조합
        return homepageService.getWithCache(Objects.requireNonNullElse(userId, Long.MIN_VALUE));
    }


}
