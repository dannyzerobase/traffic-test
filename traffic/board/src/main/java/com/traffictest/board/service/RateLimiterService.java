package com.traffictest.board.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public boolean tryConsume(String ipAddress){
        Bucket bucket = buckets.computeIfAbsent(ipAddress, this::newBucket);
        return bucket.tryConsume(1);
    }

    private Bucket newBucket(String ipAddress){
        Bandwidth limit =Bandwidth.builder().capacity(2)
                .refillGreedy(2, Duration.ofSeconds(5)).build();

        return Bucket.builder().addLimit(limit).build();
    }
}
