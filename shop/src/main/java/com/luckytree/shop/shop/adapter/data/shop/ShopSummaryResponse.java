package com.luckytree.shop.shop.adapter.data.shop;

import com.luckytree.shop.shop.adapter.jpa.shop.ShopEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;
import luckytree.poom.core.enums.ShopStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "샵 요약정보")
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopSummaryResponse {

    @Schema(description = "샵 이름")
    private String name;

    @Schema(description = "샵 상태", hidden = true)
    private ShopStatus status;

    @Schema(description = "샵 위도")
    private Double lat;

    @Schema(description = "샵 경도")
    private Double lng;

    @Schema(description = "샵 카테고리")
    private ShopCategory category;

    @Schema(description = "샵 해시태그")
    private ShopHashtag hashtag;

    public ShopSummaryResponse(ShopEntity shopEntity) {
        this.name = shopEntity.getName();
        this.status = shopEntity.getStatus();
        this.lat = shopEntity.getLat();
        this.lng = shopEntity.getLng();
        this.category = shopEntity.getCategory();
        this.hashtag = shopEntity.getHashtag();
    }
}
