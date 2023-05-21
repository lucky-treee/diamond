package com.luckytree.shop.shop.domain;

import com.luckytree.shop.common.enums.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {

    private long id;
    private long shopId;
    private long memberId;
    private String content;
    private Hashtag hashtag;

    public Review(long shopId, long memberId, String content, Hashtag hashtag) {
        this.shopId = shopId;
        this.memberId = memberId;
        this.content = content;
        this.hashtag = hashtag;
    }
}
