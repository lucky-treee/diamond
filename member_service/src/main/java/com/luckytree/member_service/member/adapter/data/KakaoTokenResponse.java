package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luckytree.member_service.common.advice.InternalServerErrorException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoTokenResponse {

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "expires_in")
    private String expiresIn;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    @JsonProperty(value = "refresh_token_expires_in")
    private String refreshTokenExpiresIn;

    @JsonProperty(value = "scope")
    private String scope;

    public String getAccessToken() {
        if (accessToken == null) {
            throw new InternalServerErrorException("정상적으로 토큰을 받지 못했습니다.");
        }
        return "Bearer " + accessToken;
    }
}
