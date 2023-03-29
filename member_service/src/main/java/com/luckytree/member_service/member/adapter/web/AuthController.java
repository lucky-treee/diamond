package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.LoginRequest;
import com.luckytree.member_service.member.adapter.data.SignupRequest;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    @Deprecated
    public void login(@RequestHeader("Authorization") String authorization) {
        authenticationUseCase.login(authorization);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupRequest signupRequest) {
        Tokens tokens = authenticationUseCase.signup(signupRequest);
        String refreshTokenCookie = authenticationUseCase.makeCookie(tokens.getRefreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie)
                .body(tokens.getAccessToken());
    }

    @PostMapping("/login/kakao")
    public ResponseEntity<Object> loginByKakao(@Valid @RequestBody LoginRequest loginRequest) {
        Tokens tokens = authenticationUseCase.login(loginRequest.getCode(), loginRequest.getRedirectUri());
        String refreshTokenCookie = authenticationUseCase.makeCookie(tokens.getRefreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie)
                .body(tokens.getAccessToken());
    }

    @PostMapping("/token")
    public ResponseEntity<Object> reissueToken(@RequestHeader("refresh-token") String refreshToken) {
        Tokens tokens = authenticationUseCase.reissue(refreshToken);
        String refreshTokenCookie = authenticationUseCase.makeCookie(tokens.getRefreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie)
                .body(tokens.getAccessToken());
    }
}
