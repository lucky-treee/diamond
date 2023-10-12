package com.luckytree.shop.shop.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

@Getter
@AllArgsConstructor
public class SearchShopsCondition {
    private Long id;
    private ShopHashtag hashtag;
    private ShopCategory category;
    private Double maxLat;
    private Double minLat;
    private Double maxLng;
    private Double minLng;
}
