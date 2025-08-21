package com.traffictest.board.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {
    private final RedisTemplate<String,Object> template;

    public void publish(ChannelTopic topic, Object dto){
        template.convertAndSend(topic.getTopic(), dto);
    }
}
