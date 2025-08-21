package com.traffictest.board.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class BoardLikeChangePublisher {

    private final ChannelTopic topic;
    private final RedisPublisher redisPublisher;

    public void publish(Long boardId) {
        redisPublisher.publish(topic, boardId);
    }

    public void publishAsync(Long boardId) {
        CompletableFuture.supplyAsync(() -> {
            redisPublisher.publish(topic, boardId);
            return null;
        });
    }
}
