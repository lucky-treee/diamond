package com.example.auth.client.feign.kakao

import com.example.auth.config.InternalFeignConfig
import com.example.auth.client.feign.kakao.data.GetKakaoTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Component
@FeignClient(
    name = "ExternalTokenFeignClient",
    url = "\${oauth2.kakao.baseUrl}",
    configuration = [InternalFeignConfig::class]
)
interface KakaoTokenFeignClient {
    @PostMapping("/oauth/token")
    fun getToken(@RequestBody tokenRequest: String): GetKakaoTokenResponse
}
