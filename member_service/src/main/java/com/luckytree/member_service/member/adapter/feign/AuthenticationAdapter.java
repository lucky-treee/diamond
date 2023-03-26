package com.luckytree.member_service.member.adapter.feign;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.adapter.data.KakaoTokenRequest;
import com.luckytree.member_service.member.adapter.data.KakaoTokenResponse;
import com.luckytree.member_service.member.adapter.data.KakaoUserInfo;
import com.luckytree.member_service.member.adapter.data.SignupRequest;
import com.luckytree.member_service.member.adapter.jpa.MemberEntity;
import com.luckytree.member_service.member.adapter.jpa.MemberRepository;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Log4j2
@RequiredArgsConstructor
@Repository
public class AuthenticationAdapter implements AuthenticationPort {

    private final MemberRepository memberRepository;
    private final KakaoTokenFeignClient kakaoTokenFeignClient;
    private final KakaoUserInfoFeignClient kakaoUserInfoFeignClient;

    @Value("${oauth2.kakao.clientId}")
    String clientId;

    @Value("${oauth2.kakao.secretKey}")
    String clientSecret;

    @Override
    public long saveMember(SignupRequest signupRequest) {
        return memberRepository.save(new MemberEntity(signupRequest)).getId();
    }

    @Override
    public long findMemberIdByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email)).getId();
    }

    @Override
    public String getUserKakaoAccessToken(String code, String redirectUri) {
        KakaoTokenResponse kakaoTokenResponse = kakaoTokenFeignClient.getToken(new KakaoTokenRequest(code, clientId, clientSecret, redirectUri).toString());
        return kakaoTokenResponse.getAccessToken();
    }

    @Override
    public String getUserKakaoEmail(String accessToken) {
        KakaoUserInfo kakaoUserInfo = kakaoUserInfoFeignClient.getUser(accessToken);

        return kakaoUserInfo.getEmail();
    }
}
