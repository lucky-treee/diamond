package com.luckytree.member.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import luckytree.poom.core.enums.ShopCategory;

@Getter
@NoArgsConstructor
public class Bookmark {

    private long memberId;
    private long shopId;
    private ShopCategory category;

    public Bookmark(long memberId, long shopId, ShopCategory category) {
        this.memberId = memberId;
        this.shopId = shopId;
        this.category = category;
    }
}