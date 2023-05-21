package com.luckytree.shop.shop.adapter.data;

import com.luckytree.shop.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop.shop.adapter.jpa.ShopEntity;
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
public class ReviewDto {

    private String shopName;
    private ShopCategory category;
    private List<String> photoUrl;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createAt;

    public ReviewDto(ReviewEntity reviewEntity, List<ReviewPhotoEntity> reviewPhotos, ShopEntity shopEntity) {
        this.shopName = shopEntity.getName();
        this.category = shopEntity.getCategory();
        this.photoUrl = reviewPhotos.stream().map(ReviewPhotoEntity::getPhotoUrl).collect(Collectors.toList());
        this.content = reviewEntity.getContent();
        this.createAt = reviewEntity.getCreateAt();
    }
}
