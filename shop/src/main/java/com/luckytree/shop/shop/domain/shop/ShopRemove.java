package com.luckytree.shop.shop.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShopRemove {
    private Long id;
    private Long shopId;
    private String comment;
    private LocalDateTime createAt;
}
