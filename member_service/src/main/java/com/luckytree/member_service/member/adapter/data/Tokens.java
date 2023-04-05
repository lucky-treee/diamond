package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tokens {
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
}
