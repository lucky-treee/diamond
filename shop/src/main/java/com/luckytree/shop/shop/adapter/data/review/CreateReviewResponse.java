package com.luckytree.shop.shop.adapter.data.review;

import com.luckytree.shop.shop.domain.review.Review;
import com.luckytree.shop.shop.domain.review.ReviewPhoto;
import lombok.AllArgsConstructor;
import luckytree.poom.core.enums.ShopHashtag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreateReviewResponse {
    private Long id;
    private long shopId;
    private long memberId;
    private String content;
    private ShopHashtag hashtag;
    private List<String> photoUrl;
    private LocalDateTime updateAt;
    private LocalDateTime createAt;

    public CreateReviewResponse(Review review, List<ReviewPhoto> reviewPhotos) {
        this.id = review.getId();
        this.shopId = review.getShopId();
        this.memberId = review.getMemberId();
        this.content = review.getContent();
        this.hashtag = review.getHashtag();
        this.photoUrl = reviewPhotos.stream().map(ReviewPhoto::getPhotoUrl).collect(Collectors.toList());
        this.updateAt = review.getUpdateAt();
        this.createAt = review.getCreateAt();
    }
}
