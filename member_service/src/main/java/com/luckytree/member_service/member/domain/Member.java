package com.luckytree.member_service.member.domain;


import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import lombok.Getter;

@Getter
public class Member {

    private String nickname;

    private String email;

    private Status status;

    private Photo photo;

    public Member(SignupDto signUpDto) {
        this.nickname = signUpDto.getNickname();
        this.email = signUpDto.getEmail();
        this.status = Status.NORMAL;
        this.photo = signUpDto.getPhoto();
    }

    public Member(MemberEntity memberEntity) {
        this.email = memberEntity.getEmail();
        this.nickname = memberEntity.getNickname();
        this.photo = memberEntity.getPhoto();
        this.status = memberEntity.getStatus();
    }

    public MemberEntity toEntity() {
        return new MemberEntity(nickname, email, photo);
    }

    public void updateNicknameAndPhoto(String nickname, Photo photo) {
        this.nickname = nickname;
        this.photo = photo;
    }

    public void withdrawalMember(Status status) {
        this.status = Status.LEAVE;
    }
}
