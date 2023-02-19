package com.luckytree.member_service.member.adapter.web;

import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.TokenDto;
import com.luckytree.member_service.member.application.port.incoming.AuthenticationUseCase;
import com.luckytree.member_service.member.domain.Authentication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody Authentication authentication) {
        authenticationUseCase.authenticate(authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupDto signupDto) {
        TokenDto tokenDto = authenticationUseCase.signup(signupDto);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
