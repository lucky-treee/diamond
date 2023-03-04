package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.member.adapter.data.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "kakaoFeignClient", url = "${oauth2.kakao.baseUrl}", configuration = KakaoFeignConfiguration.class)
public interface KakaoTokenFeignClient {

    @PostMapping(value = "/oauth/token")
    KakaoTokenResponse getToken(@RequestBody String kakaoTokenRequest);
}
