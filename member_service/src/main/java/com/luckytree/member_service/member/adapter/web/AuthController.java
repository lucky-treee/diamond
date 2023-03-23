package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.common.annotation.LoginMemberId;
import com.luckytree.member_service.member.adapter.data.KakaoLoginDto;
import com.luckytree.member_service.member.adapter.data.LoginDto;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader("Authorization") String authorization) {
        log.info("로그인 요청 :: Authorization :: " + authorization);
        authenticationUseCase.login(authorization);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupDto signupDto) {
        Tokens tokens = authenticationUseCase.signup(signupDto);

        ResponseCookie responseCookie = ResponseCookie
                .from("refresh-token", tokens.getRefreshToken())
                .domain("c0dewave.com")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .maxAge(60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(tokens.getAccessToken());
    }

    @PostMapping("/login/kakao")
    public ResponseEntity<?> loginByKakao(@Valid @RequestBody KakaoLoginDto kakaoLoginDto) {
        log.info("카카오 로그인 요청 :: 인가코드 :: " + kakaoLoginDto.getCode());
        Tokens tokens = authenticationUseCase.login(kakaoLoginDto.getCode(), kakaoLoginDto.getRedirectUri());

        ResponseCookie responseCookie = ResponseCookie
                .from("refresh-token", tokens.getRefreshToken())
                .domain("c0dewave.com")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .maxAge(60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(tokens.getAccessToken());
    }
}
