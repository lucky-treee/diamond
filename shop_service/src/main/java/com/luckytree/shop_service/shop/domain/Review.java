package com.luckytree.shop_service.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {

    private long id;
    private long shopId;
    private long memberId;
    private String content;

    public Review(long shopId, long memberId, String content) {
        this.shopId = shopId;
        this.memberId = memberId;
        this.content = content;
    }
}
