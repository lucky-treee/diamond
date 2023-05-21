package com.example.auth.client.feign.kakao.data

import com.fasterxml.jackson.annotation.JsonProperty

data class GetKakaoUserResponse(
    @JsonProperty("id")
    val id: String,

    @JsonProperty("connected_at")
    val connectedAt: String,

    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount,
)
