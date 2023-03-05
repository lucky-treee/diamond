package com.luckytree.member_service.member.adapter.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenResponse {

    private String tokenType;
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String refreshTokenExpiresIn;
    private String scope;

    public String getAccessToken() {
        return "Bearer " + accessToken;
    }
}
