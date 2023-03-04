package com.luckytree.member_service.member.adapter.data;

public class KakaoTokenRequest {

    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String grantType;

    public KakaoTokenRequest(String code, String clientId, String clientSecret, String redirectUri){
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.grantType = "authorization_code";
    }

    @Override
    public String toString() {
        return
                "code=" + code + '&' +
                        "client_id=" + clientId + '&' +
                        "client_secret=" + clientSecret + '&' +
                        "redirect_uri=" + redirectUri + '&' +
                        "grant_type=" + grantType;
    }
}
