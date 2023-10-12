package com.luckytree.shop.shop.adapter.data.shop;

import com.luckytree.shop.shop.domain.shop.SearchShopsCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;
import luckytree.poom.core.enums.ShopHashtag;

@NoArgsConstructor
@Getter
@Setter
public class SearchShopsRequest {

    private Long id;
    private ShopHashtag hashtag;
    private ShopCategory category;
    private Double maxLat;
    private Double minLat;
    private Double maxLng;
    private Double minLng;

    public SearchShopsCondition toSearchShopsCondition() {
        return new SearchShopsCondition(
                id,
                hashtag,
                category,
                maxLat,
                minLat,
                maxLng,
                minLng
        );
    }
}
