package com.luckytree.shop.shop.domain.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.exceptions.BadRequestException;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Review {

    private Long id;
    private Long shopId;
    private Long memberId;
    private String content;
    private ShopHashtag hashtag;
    private List<ReviewPhoto> reviewPhotos;
    private LocalDateTime updateAt;
    private LocalDateTime createAt;

    public Review(Long id, Long shopId, Long memberId, String content, ShopHashtag hashtag, LocalDateTime updateAt, LocalDateTime createAt) {
        this.id = id;
        this.shopId = shopId;
        this.memberId = memberId;
        this.content = content;
        this.hashtag = hashtag;
        this.updateAt = updateAt;
        this.createAt = createAt;
    }

    public Review(Long shopId, Long memberId, String content, ShopHashtag hashtag) {
        this.shopId = shopId;
        this.memberId = memberId;
        this.content = content;
        this.hashtag = hashtag;
    }

    public void setReviewPhotos(List<ReviewPhoto> reviewPhotos) {
        this.reviewPhotos = reviewPhotos;
    }

    public void check() {
        if (this.createAt.plusDays(7).isBefore(LocalDateTime.now())) {
            throw new BadRequestException("수정 가능 기간 초과");
        }
    }

    public void update(ReviewDetail reviewDetail) {
        this.content = reviewDetail.getContent();
        this.hashtag = reviewDetail.getHashtag();
    }
}
