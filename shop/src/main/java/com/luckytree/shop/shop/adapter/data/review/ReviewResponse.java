package com.luckytree.shop.shop.adapter.data.review;

import com.luckytree.shop.shop.adapter.jpa.review.ReviewEntity;
import com.luckytree.shop.shop.adapter.jpa.review.ReviewPhotoEntity;
import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponse {

    private String shopName;
    private ShopCategory category;
    private List<String> photoUrl;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createAt;

    public ReviewResponse(ReviewEntity reviewEntity, List<ReviewPhotoEntity> reviewPhotos, ShopEntity shopEntity) {
        this.shopName = shopEntity.getName();
        this.category = shopEntity.getCategory();
        this.photoUrl = reviewPhotos.stream().map(ReviewPhotoEntity::getPhotoUrl).collect(Collectors.toList());
        this.content = reviewEntity.getContent();
        this.createAt = reviewEntity.getCreateAt();
    }
}
