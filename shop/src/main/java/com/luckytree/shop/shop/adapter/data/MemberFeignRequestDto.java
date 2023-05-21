package com.luckytree.shop.shop.adapter.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luckytree.poom.core.enums.ShopCategory;

@Getter
@Setter
@NoArgsConstructor
public class MemberFeignRequestDto {

    private long memberId;
    private long shopId;
    private ShopCategory category;

    public MemberFeignRequestDto(long memberId, long shopId, ShopCategory category) {
        this.memberId = memberId;
        this.shopId = shopId;
        this.category = category;
    }
}