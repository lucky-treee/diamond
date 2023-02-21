package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.AuthenticateResult;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.TokenDto;
import com.luckytree.member_service.member.domain.Authentication;

public interface AuthenticationUseCase {

    AuthenticateResult login(Authentication authentication);

    TokenDto signup(SignupDto signUpDto);
}
