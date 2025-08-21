package com.traffictest.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class FutureConfig {

    @Bean
    public ExecutorService customExecutor(){
        return new ThreadPoolExecutor(
                20,100,60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200)
        );
    }
}
