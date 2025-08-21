package com.traffictest.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 국가 데이터, 국가 코드, 국가당 pricing
public class PageItems {
    private Long id;
    private String subject;
    private String imgUrl;
    Long price;
    Long reviewCount;
}
