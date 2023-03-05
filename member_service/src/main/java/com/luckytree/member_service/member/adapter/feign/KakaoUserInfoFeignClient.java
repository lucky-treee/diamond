package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.member.adapter.data.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "kakaoUserInfoFeignClient", url = "${oauth2.kakao.infoUrl}", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface KakaoUserInfoFeignClient {

    @GetMapping("/v2/user/me")
    KakaoUserInfo getUser(@RequestHeader(name = "Authorization") String Authorization);
}
