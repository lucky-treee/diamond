package com.luckytree.member_service.member.adapter.persistence;

import com.luckytree.member_service.common.advice.NotFoundException;
import com.luckytree.member_service.member.adapter.data.KakaoTokenRequest;
import com.luckytree.member_service.member.adapter.data.KakaoTokenResponse;
import com.luckytree.member_service.member.adapter.data.KakaoUserInfo;
import com.luckytree.member_service.member.adapter.feign.KakaoTokenFeignClient;
import com.luckytree.member_service.member.adapter.feign.KakaoUserInfoFeignClient;
import com.luckytree.member_service.member.application.port.outgoing.AuthenticationPort;
import com.luckytree.member_service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

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

    @Value("${oauth2.kakao.redirectUri}")
    String redirectUri;

    @Override
    public long saveMember(Member member) {
        return memberRepository.save(member.toEntity()).getId();
    }

    @Override
    public long findMemberIdByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("회원이 아닙니다. email : " + email)).getId();
    }

    @Override
    public String getUserKakaoAccessToken(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoTokenFeignClient.getToken(new KakaoTokenRequest(code, clientId, clientSecret, redirectUri).toString());
        return kakaoTokenResponse.getAccessToken();
    }

    @Override
    public String getUserKakaoEmail(String accessToken) {
        KakaoUserInfo kakaoUserInfo = kakaoUserInfoFeignClient.getUser(accessToken);
        return kakaoUserInfo.getEmail();
    }
}
