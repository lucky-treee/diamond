package com.luckytree.shop_service.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewPhoto {

    private long id;
    private long reviewId;
    private String photoUrl;

    public ReviewPhoto(long reviewId, String photoUrl) {
        this.reviewId = reviewId;
        this.photoUrl = photoUrl;
    }
}
