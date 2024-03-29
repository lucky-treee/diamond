package com.example.auth.client.feign.kakao.data

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

data class GetKakaoUserEmailRequest(
    @field:NotNull
    val code: String,

    @field:NotNull
    @JsonProperty(value = "redirect_uri")
    val redirectUri: String,
)
