package com.luckytree.shop_service.shop.adapter.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShopReviewResponse {

    private String memberPhoto;
    private String memberNickname;
    private String Photo;
    private LocalDateTime createAt;
}
