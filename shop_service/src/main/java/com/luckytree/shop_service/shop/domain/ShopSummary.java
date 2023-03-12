package com.luckytree.shop_service.shop.domain;

import com.luckytree.shop_service.shop.adapter.data.Category;
import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "샵 요약정보")
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopSummary {

    @Schema(description = "샵 이름")
    private String name;

    @Schema(description ="샵 상태", hidden = true)
    private ShopStatus status;

    @Schema(description = "샵 위도")
    private Double lat;

    @Schema(description = "샵 경도")
    private Double lng;

    @Schema(description = "샵 카테고리")
    private Category category;

    @Schema(description = "샵 해시태그")
    private Hashtag hashtag;

    public ShopSummary(ShopEntity shopEntity) {
        this.name = shopEntity.getName();
        this.status = shopEntity.getStatus();
        this.lat = shopEntity.getLat();
        this.lng = shopEntity.getLng();
        this.category = shopEntity.getCategory();
        this.hashtag = shopEntity.getHashtag();
    }
}
