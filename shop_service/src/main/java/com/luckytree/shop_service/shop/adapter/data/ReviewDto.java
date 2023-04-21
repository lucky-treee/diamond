package com.luckytree.shop_service.shop.adapter.data;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ReviewPhotoEntity;
import com.luckytree.shop_service.shop.adapter.jpa.ShopEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private String name;
    private Category category;
    private List<String> photo;
    private String content;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createAt;

    public ReviewDto(ReviewEntity reviewEntity) {
        this.name = reviewEntity.getShopEntity().getName();
        this.category = reviewEntity.getShopEntity().getCategory();
        //this.photo ;
        this.content = reviewEntity.getContent();
        this.createAt = reviewEntity.getCreateAt();

    }
}
