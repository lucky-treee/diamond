package com.luckytree.shop_service.shop.adapter.data;

import com.luckytree.shop_service.common.enums.Category;
import com.luckytree.shop_service.common.enums.Hashtag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "샵 리뷰 조회 DTO")
public class ShopReviewDto {

    private String name;
    private Category category;
    private String address;
    private int reviewCount;
    private Hashtag hashtag;
    private List<ShopReviewResponse> review;
}
