package com.luckytree.shop.shop.adapter.feign;


import com.luckytree.shop.common.config.FeignClientConfig;
import com.luckytree.shop.shop.adapter.data.MemberFeignRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service", configuration = FeignClientConfig.class)
public interface MemberFeignClient {

    @PostMapping("/v1/bookmarks")
    void saveBookmark(MemberFeignRequestDto memberFeignRequestDto);

    @DeleteMapping("/v1/bookmarks/{memberId}")
    void deleteBookmark(@PathVariable long memberId, @RequestParam("shopId") long shopId);
}
