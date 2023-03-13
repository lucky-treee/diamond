package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.common.config.FeignClientConfig;
import com.luckytree.member_service.member.adapter.data.ShopDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="findShopDetail", url="http://localhost:8000" , configuration={FeignClientConfig.class})
public interface BookmarkFeignClient {

    @GetMapping("v1/shops")
    List<ShopDetailDto> findShopByIds(@RequestParam List<Long> id);
}
