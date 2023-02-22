package com.luckytree.member_service.member.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {

    private String code;

    private String accessToken;

    private String refreshToken;
}
