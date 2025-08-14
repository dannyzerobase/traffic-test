package com.traffictest.board.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MetricsCollector {
    private final PrometheusMeterRegistry registry;

    public void count(final String metricsName, final String... tags) {
        try {
            Counter counter = registry.counter(metricsName, tags);
            counter.increment();
        } catch (Exception e) {
            log.error("Error while counting metrics", e);
        }
    }

}
