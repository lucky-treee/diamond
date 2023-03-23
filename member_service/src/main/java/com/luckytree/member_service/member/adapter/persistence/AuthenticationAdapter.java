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
    public long saveMember(Member member) {
        return memberRepository.save(member.toEntity()).getId();
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
        log.info("카카오 이메일 받기 요청 :: 액세스 토큰 :: " + accessToken);
        KakaoUserInfo kakaoUserInfo = kakaoUserInfoFeignClient.getUser(accessToken);
        log.info("이메일 요청 결과 id :: " + kakaoUserInfo.getId());
        log.info("이메일 요청 결과 getConnectedAt :: " + kakaoUserInfo.getConnectedAt());
        log.info("이메일 요청 결과 getKakaoAccount :: " + kakaoUserInfo.getKakaoAccount());
        log.info("이메일 요청 결과 isEmailNeedsAgreement :: " + kakaoUserInfo.getKakaoAccount().isEmailNeedsAgreement());
        log.info("이메일 요청 결과 isEmailValid :: " + kakaoUserInfo.getKakaoAccount().isEmailValid());
        log.info("이메일 요청 결과 isEmailVerified :: " + kakaoUserInfo.getKakaoAccount().isEmailVerified());
        log.info("이메일 요청 결과 isHasEmail :: " + kakaoUserInfo.getKakaoAccount().isHasEmail());
        log.info("이메일 요청 결과 getEmail :: " + kakaoUserInfo.getKakaoAccount().getEmail());

        return kakaoUserInfo.getEmail();
    }
}
