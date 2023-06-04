package com.luckytree.shop.shop.adapter.data.shop;

import com.luckytree.shop.shop.domain.shop.Shop;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

@NoArgsConstructor
@Getter
@Setter
public class SearchShopsResponse {
    @Schema(description = "샵 이름")
    private String name;

    @Schema(description = "샵 위도")
    private Double lat;

    @Schema(description = "샵 경도")
    private Double lng;

    @Schema(description = "샵 카테고리")
    private ShopCategory category;

    @Schema(description = "샵 해시태그")
    private ShopHashtag hashtag;

    public SearchShopsResponse(Shop shop) {
        this.name = shop.getName();
        this.lat = shop.getLat();
        this.lng = shop.getLng();
        this.category = shop.getCategory();
        this.hashtag = shop.getHashtag();
    }
}
