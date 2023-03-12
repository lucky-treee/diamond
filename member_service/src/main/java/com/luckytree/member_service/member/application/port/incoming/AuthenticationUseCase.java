package com.luckytree.member_service.member.application.port.incoming;

import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.data.Tokens;
import com.luckytree.member_service.member.adapter.data.LoginDto;

/**
 * 로그인 시나리오 (플로우 이해를 위한 임시 주석)
 *
 * 사용자가 카카오 로그인 버튼을 누른다.
 *  사용자에게 품 JWT 토큰이 있다면 /poom/login 으로 요청한다.
 *      JWT 토큰을 인증하고 로그인에 실패하거나 성공한다.
 *          실패: 토큰 검증 오류, 토큰 만료 등
 *          성공: 정상적인 토큰
 *  사용자에게 품 JWT 토큰이 없다면 /kakao/login 으로 요청한다.
 *      인가코드를 발급받은 뒤에 품 서버로 전달한다.
 *      품 서버에서 카카오 서버로 토큰을 요청한다.
 *      발급 받은 토큰으로 유저 리소스를 요청한다.
 *          유저 리소스(이메일)이 품 DB에 있다면 로그인 성공(품 JWT 전달, 회원이지만 토큰이 없던 케이스)
 *          없다면 클라이언트로 404와 이메일을 던진다.
 *              클라이언트는 404를 받으면 회원가입 화면으로 넘어간다.
 *              유저로부터 닉네임과 사진을 입력받는다.
 *              품 서버의 /poom/signup 으로 전달한다.
 *                  서버는 전달받은 닉네임, 사진, 이메일로 회원을 생성한다.
 *                  품 JWT를 생성하여 클라이언트로 내려준다.
 */

public interface AuthenticationUseCase {

    void login(LoginDto loginDto);

    Tokens signup(SignupDto signUpDto);

    Tokens login(String code);
}
