package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.TokenDto;
import com.luckytree.member_service.member.adapter.data.LoginDto;

public interface AuthenticationUseCase {

    void login(LoginDto loginDto);

    TokenDto signup(SignupDto signUpDto);

    TokenDto login(String code);
}
