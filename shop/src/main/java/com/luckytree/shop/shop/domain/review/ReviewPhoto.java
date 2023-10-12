package com.luckytree.shop.shop.domain.review;

import lombok.Getter;

@Getter
public class ReviewPhoto {

    private Long id;
    private Long reviewId;
    private String photoUrl;

    public ReviewPhoto(Long reviewId, String photoUrl) {
        this.reviewId = reviewId;
        this.photoUrl = photoUrl;
    }

    public ReviewPhoto(Long id, Long reviewId, String photoUrl) {
        this.id = id;
        this.reviewId = reviewId;
        this.photoUrl = photoUrl;
    }
}
