package com.luckytree.shop_service.shop.application.port.outgoing;

import com.luckytree.shop_service.shop.adapter.data.MemberFeignRequestDto;

public interface MemberFeignClientPort {

    void saveBookmark (MemberFeignRequestDto memberFeignRequestDto);
}