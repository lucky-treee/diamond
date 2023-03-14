package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.common.config.FeignClientConfig;
import com.luckytree.member_service.member.adapter.data.MyBookmarksDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="shop-service", configuration={FeignClientConfig.class})
public interface ShopFeignClient {

    @PostMapping("/v1/shops")
    MyBookmarksDto findBookmarksByIds(@RequestBody List<Long> shopIds);
}
