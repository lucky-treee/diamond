package com.luckytree.member_service.member.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopFeignRequestDto {

    private final List<Long> shopIds;
}
