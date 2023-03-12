package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luckytree.member_service.common.advice.InternalServerErrorException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoUserInfo {

    private String id;

    @JsonProperty(value = "connected_at")
    private String connectedAt;

    @JsonProperty(value = "kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonIgnore
    public String getEmail() {
        if (kakaoAccount == null) {
            throw new InternalServerErrorException("카카오 계정정보를 받지 못했습니다.");
        }
        return getKakaoAccount().getEmail();
    }
}
