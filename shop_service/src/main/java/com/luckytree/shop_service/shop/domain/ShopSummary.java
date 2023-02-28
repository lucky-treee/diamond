package com.luckytree.shop_service.shop.domain;

import com.luckytree.shop_service.shop.adapter.persistence.ShopEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopSummary {

    private String name;
    private ShopStatus status;
    private Double lat;
    private Double lng;
    private String category;
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
