package com.traffictest.statistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delay")
public class DelayController {
    @GetMapping
    public String delayGet() throws InterruptedException {
        Thread.sleep(500);
        return "hello";
    }
}
