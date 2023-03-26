package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.SignupRequest;
import com.luckytree.member_service.member.adapter.data.Tokens;

public interface AuthenticationUseCase {

    void login(String authorization);
    Tokens signup(SignupRequest signUpRequest);
    Tokens login(String code, String redirectUri);
    String makeCookie(String refreshToken);
}
