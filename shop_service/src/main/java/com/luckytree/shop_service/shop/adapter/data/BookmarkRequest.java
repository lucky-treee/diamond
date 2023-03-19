package com.luckytree.shop_service.shop.adapter.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkRequest {

    private long shopId;
    private long memberId;
}