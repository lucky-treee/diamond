package com.example.auth.controller

import com.example.auth.model.dto.AccessTokenResponse
import com.example.auth.model.dto.GetKakaoUserEmailRequest
import com.example.auth.model.dto.GetKakaoUserEmailResponse
import com.example.auth.model.dto.GenerateTokenRequest
import com.example.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("v1/auth")
@RestController
class AuthController(
    val authService: AuthService,
) {
    @PostMapping("/token/generate")
    @Operation(summary = "토큰 발급")
    fun generate(@RequestBody @Valid generateTokenRequest: GenerateTokenRequest): ResponseEntity<AccessTokenResponse> {
        return authService.generate(generateTokenRequest)
    }

    @PostMapping("/token/invalidate")
    @Operation(summary = "강제 로그아웃(미동작)")
    fun invalidate() {
        // TODO
    }

    @PostMapping("/token/refresh")
    @Operation(summary = "토큰 재발급")
    fun refresh(@CookieValue("refresh-token") refreshToken: String): ResponseEntity<AccessTokenResponse> {
        return authService.refresh(refreshToken = refreshToken)
    }

    @PostMapping("/kakao")
    @Operation(summary = "카카오 로그인")
    fun getKakaoUser(@RequestBody @Valid getKakaoUserEmailRequest: GetKakaoUserEmailRequest): GetKakaoUserEmailResponse {
        return authService.getKakaoUser(getKakaoUserEmailRequest)
    }
}
