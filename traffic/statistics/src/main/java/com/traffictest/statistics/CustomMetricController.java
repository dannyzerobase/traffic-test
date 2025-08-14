package com.traffictest.statistics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/metrics")
public class CustomMetricController {

    @GetMapping
    public String test(){
        Random random = new Random();
        if(random.nextInt()%4==0){
            throw new RuntimeException();
        }
        return "Success";
    }
}
