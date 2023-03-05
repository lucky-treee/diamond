package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
