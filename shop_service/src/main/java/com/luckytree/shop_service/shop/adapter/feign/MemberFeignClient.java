package com.luckytree.shop_service.shop.adapter.feign;


import com.luckytree.shop_service.common.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service", configuration = FeignClientConfig.class)
public interface MemberFeignClient {

    @DeleteMapping("v1/bookmarks/{memberId}")
    void deleteBookmark(@PathVariable long memberId, @RequestParam("shopId") long shopId);
}
