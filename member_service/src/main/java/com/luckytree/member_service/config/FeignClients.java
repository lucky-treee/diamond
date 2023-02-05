package com.luckytree.member_service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "notification-service", configuration = FeignClientConfig.class)
public interface FeignClients {
}
