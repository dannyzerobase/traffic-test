package com.traffictest.board.controller;

import com.traffictest.board.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@RestController
@RequestMapping("/limiter")
@RequiredArgsConstructor
public class RateLimitController {
    private final RateLimiterService rateLimiterService;

    @GetMapping
    public ResponseEntity<String> limitedEndpoint(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        if (rateLimiterService.tryConsume(ip)){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(TOO_MANY_REQUESTS).body("too many requests");
    }
}
