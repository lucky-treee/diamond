package com.luckytree.shop.shop.domain.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@AllArgsConstructor
public class ReviewDetail {
    private long id;

    private String content;

    private ShopHashtag hashtag;
}
