package com.luckytree.member_service.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Authentication {

    private String authorizationCode;

    private String accessToken;
}
