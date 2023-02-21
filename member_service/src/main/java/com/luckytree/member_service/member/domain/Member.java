package com.luckytree.member_service.member.domain;


import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import lombok.Getter;

@Getter
public class Member {

    private final String nickname;

    private final String email;

    private final Status status;

    private final Photo photo;

    public Member(SignupDto signUpDto) {
        this.nickname = signUpDto.getNickname();
        this.email = signUpDto.getEmail();
        this.status = Status.NORMAL;
        this.photo = signUpDto.getPhoto();
    }

    public MemberEntity toEntity() {
        return new MemberEntity(nickname, email, photo);
    }
}
