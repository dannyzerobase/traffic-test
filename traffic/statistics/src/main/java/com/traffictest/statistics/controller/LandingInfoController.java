package com.traffictest.statistics.controller;

import com.traffictest.common.dto.PageItems;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/landing")
public class LandingInfoController {
    private final Faker faker = new Faker();

    @GetMapping
    public List<PageItems> home(@RequestParam(required = false) Long userId) throws InterruptedException {

        if(userId == null){
            return getDefault();
        }
        return getForUsers(userId);

    }
    // 매 분 갱신되지만 정확하지는 않다.
    private List<PageItems> getDefault() throws InterruptedException {
        Thread.sleep(300);
        List<PageItems> items = new ArrayList<>();
        for(int i=0;i<10;i++){
            PageItems item = PageItems.builder()
                    .id(i+1L)
                    .subject("Welcome ! 아이템 추천 "+(i+1))
                    .imgUrl(faker.internet().image())
                    .price(faker.number().randomNumber())
                    .reviewCount(faker.number().randomNumber())
                    .build();
            items.add(item);
        }
        return items;
    }
    private List<PageItems> getForUsers(Long userId) throws InterruptedException {
        Thread.sleep(300);
        List<PageItems> items = new ArrayList<>();
        for(int i=0;i<10;i++){
            PageItems item = PageItems.builder()
                    .id(i+1L)
                    .subject(userId+ "님을 위한 추천 "+(i+1))
                    .imgUrl(faker.internet().image())
                    .price(faker.number().randomNumber())
                    .reviewCount(faker.number().randomNumber())
                    .build();
            items.add(item);
        }
        return items;
    }


}
