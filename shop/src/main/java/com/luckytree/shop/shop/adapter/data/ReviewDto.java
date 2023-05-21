package com.luckytree.shop.shop.adapter.data;

import com.luckytree.shop.common.enums.Category;
import com.luckytree.shop.shop.adapter.jpa.ReviewEntity;
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

    private String shopName;
    private Category category;
    private List<String> photoUrl;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createAt;

    public ReviewDto(ReviewEntity reviewEntity) {
        this.shopName = reviewEntity.getShopEntity().getName();
        this.category = reviewEntity.getShopEntity().getCategory();
        this.content = reviewEntity.getContent();
        this.createAt = reviewEntity.getCreateAt();
    }
}
