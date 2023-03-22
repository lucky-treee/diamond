package com.luckytree.member_service.member.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tokens {
    private String accessToken;
    private String refreshToken;
}
