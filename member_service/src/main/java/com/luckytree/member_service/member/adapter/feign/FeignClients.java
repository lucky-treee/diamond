package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.common.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "notification-service", configuration = FeignClientConfig.class)
public interface FeignClients {
}
