package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.KakaoLoginDto;
import com.luckytree.member_service.member.adapter.data.LoginDto;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto) {
        authenticationUseCase.login(loginDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDto signupDto) {
        Tokens tokens = authenticationUseCase.signup(signupDto);

        ResponseCookie responseCookie = ResponseCookie
                .from("refresh-token", tokens.refreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(tokens.accessToken());
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<?> loginByKakao(@Valid @RequestBody KakaoLoginDto kakaoLoginDto) {
        Tokens tokens = authenticationUseCase.login(kakaoLoginDto.getCode(), kakaoLoginDto.getRedirectUri());

        ResponseCookie responseCookie = ResponseCookie
                .from("refresh-token", tokens.refreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(tokens.accessToken());
    }
}
