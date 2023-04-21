package com.luckytree.shop_service.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {

    private long shopId;
    private long memberId;
    private String content;
}
