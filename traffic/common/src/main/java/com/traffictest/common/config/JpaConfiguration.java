package com.traffictest.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "com.traffictest.common.entity")
@EnableJpaRepositories(basePackages = "com.traffictest.common.entity")
public class JpaConfiguration {
}
