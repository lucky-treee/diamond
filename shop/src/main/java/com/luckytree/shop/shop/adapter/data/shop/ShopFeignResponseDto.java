package com.luckytree.shop.shop.adapter.data.shop;

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
