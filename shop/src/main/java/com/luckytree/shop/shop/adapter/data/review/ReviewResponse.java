package com.luckytree.shop.shop.adapter.data.review;

import com.luckytree.shop.shop.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private String shopName;
    private ShopCategory category;
    private List<String> photoUrl;
    private String content;
    private LocalDateTime createAt;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.createAt = review.getCreateAt();
    }
}
