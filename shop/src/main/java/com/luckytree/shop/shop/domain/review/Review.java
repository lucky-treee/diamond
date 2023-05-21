package com.luckytree.shop.shop.domain.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@AllArgsConstructor
public class Review {

    private long id;
    private long shopId;
    private long memberId;
    private String content;
    private ShopHashtag hashtag;

    public Review(long shopId, long memberId, String content, ShopHashtag hashtag) {
        this.shopId = shopId;
        this.memberId = memberId;
        this.content = content;
        this.hashtag = hashtag;
    }
}
