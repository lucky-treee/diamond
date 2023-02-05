package com.luckytree.shop_service.shop.adapter.out.external;


import com.luckytree.shop_service.common.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service", configuration = FeignClientConfig.class)
public interface FeignClients {

    @GetMapping("member/internal-test")
    String call(@RequestParam("name")String name);
}
