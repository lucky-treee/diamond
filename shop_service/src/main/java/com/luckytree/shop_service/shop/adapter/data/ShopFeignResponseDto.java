package com.luckytree.shop_service.shop.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopFeignResponseDto {

    private final List<Long> shopIds;
}
