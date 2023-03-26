package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.adapter.data.LoginDto;

public interface AuthenticationUseCase {

    void login(String authorization);
    Tokens signup(SignupDto signUpDto);
    Tokens login(String code, String redirectUri);
}
