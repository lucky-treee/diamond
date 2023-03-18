package com.luckytree.shop_service.shop.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopFeignResponseDto {

    private List<Long> shopIds;
}
