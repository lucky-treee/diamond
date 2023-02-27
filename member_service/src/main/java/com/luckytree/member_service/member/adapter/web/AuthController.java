package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.TokenDto;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.adapter.data.LoginDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenDto> signup(@Valid @RequestBody SignupDto signupDto) {
        TokenDto tokenDto = authenticationUseCase.signup(signupDto);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    @GetMapping("/login/kakao")
    public ResponseEntity<TokenDto> loginByKakao(@RequestParam(value = "code") String code) {
        return new ResponseEntity<>(authenticationUseCase.login(code), HttpStatus.OK);
    }
}
