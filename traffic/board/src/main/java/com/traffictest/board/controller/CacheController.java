package com.traffictest.board.controller;

import com.traffictest.board.dto.CacheObject;
import com.traffictest.board.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @GetMapping("/caffeine")
    public CacheObject getFromCaffeine(@RequestParam Long id){
        return cacheService.getFromCaffeine(id);
    }

    @GetMapping("/redis")
    public CacheObject getFromRedis(@RequestParam Long id){
        return cacheService.getFromRedis(id);
    }
}
