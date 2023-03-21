package com.luckytree.shop_service.shop.adapter.feign;


import com.luckytree.shop_service.common.config.FeignClientConfig;
import com.luckytree.shop_service.shop.adapter.data.MemberFeignRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "member-service", configuration = FeignClientConfig.class)
public interface MemberFeignClient {

    @PostMapping("v1/bookmarks")
    void saveBookmark(MemberFeignRequestDto memberFeignRequestDto);
}