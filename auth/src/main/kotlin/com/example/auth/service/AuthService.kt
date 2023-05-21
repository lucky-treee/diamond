package com.example.auth.service

import com.example.auth.client.feign.kakao.KakaoFeignRepository
import com.example.auth.domain.member.*
import com.example.auth.client.feign.kakao.data.*
import com.example.auth.client.feign.member.MemberFeignRepository
import com.example.auth.client.feign.member.data.MemberFeignRequest
import luckytree.poom.core.jwt.Claims
import luckytree.poom.core.jwt.Role
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    val jwt: Jwt,
    val kakaoFeignRepository: KakaoFeignRepository,
    val memberFeignRepository: MemberFeignRepository,

    @Value("\${oauth2.kakao.clientId}")
    val clientId: String,

    @Value("\${oauth2.kakao.secretKey}")
    val clientSecret: String,
) {
    fun generate(generateTokenRequest: GenerateTokenRequest): ResponseEntity<AccessTokenResponse> {
        val member = memberFeignRepository.findByIdAndEmail(MemberFeignRequest(generateTokenRequest)).toDomain()

        val token = Claims(
            memberId = member.id,
            role = member.role(),
        ).run(jwt::generateToken)

        val httpHeaders = HttpHeaders()
        httpHeaders.add("refresh-token", token.refreshToken)

        return ResponseEntity<AccessTokenResponse>(AccessTokenResponse(token.accessToken), httpHeaders, HttpStatus.OK)
    }

    fun invalidate() {
        TODO("토큰 무효화")
    }

    fun refresh(refreshToken: String): ResponseEntity<AccessTokenResponse> {
        val token = jwt.refreshToken(refreshToken)

        val httpHeaders = HttpHeaders()
        httpHeaders.add("refresh-token", token.refreshToken)

        return ResponseEntity<AccessTokenResponse>(AccessTokenResponse(token.accessToken), httpHeaders, HttpStatus.OK)
    }

    fun getKakaoUser(getKakaoUserEmailRequest: GetKakaoUserEmailRequest): GetKakaoUserEmailResponse {
        val kakaoTokenResponse: GetKakaoTokenResponse = kakaoFeignRepository.getToken(
            GetKakaoTokenRequest(
                code = getKakaoUserEmailRequest.code,
                redirectUri = getKakaoUserEmailRequest.redirectUri,
                clientSecret = clientSecret,
                clientId = clientId,
            ).toString()
        )
        val token: String = "Bearer ${kakaoTokenResponse.accessToken}"
        val kakaoUserResponse: GetKakaoUserResponse = kakaoFeignRepository.getUser(token)

        return GetKakaoUserEmailResponse(kakaoUserResponse.kakaoAccount.email)
    }
}
