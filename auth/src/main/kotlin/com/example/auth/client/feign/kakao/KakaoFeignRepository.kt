package com.example.auth.client.feign.kakao

import org.springframework.stereotype.Repository

@Repository
class KakaoFeignRepository(
    private val kakaoTokenFeignClient: KakaoTokenFeignClient,
    private val kakaoUserFeignClient: KakaoUserFeignClient,
) {
    fun getToken(tokenRequest: String) = kakaoTokenFeignClient.getToken(tokenRequest)

    fun getUser(authorization: String) = kakaoUserFeignClient.getUser(authorization)
}
