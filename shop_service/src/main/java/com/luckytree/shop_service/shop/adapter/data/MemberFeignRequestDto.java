package com.luckytree.shop_service.shop.adapter.data;

import com.luckytree.shop_service.common.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberFeignRequestDto {

    private long memberId;
    private long shopId;
    private Category category;

    public MemberFeignRequestDto(long memberId, long shopId, Category category) {
        this.memberId = memberId;
        this.shopId = shopId;
        this.category = category;
    }
}