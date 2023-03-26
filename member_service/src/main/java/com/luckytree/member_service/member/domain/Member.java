package com.luckytree.member_service.member.domain;


import com.luckytree.member_service.common.enums.Status;
import com.luckytree.member_service.member.adapter.data.SignupDto;
import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import lombok.Getter;

@Getter
public class Member {

    private String nickname;

    private String email;

    private Status status;

    private String photo;

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

    public Member(String nickname, String email, Status status, String photo) {
        this.nickname = nickname;
        this.email = email;
        this.status = status;
        this.photo = photo;
    }

    public MemberEntity toEntity() {
        return new MemberEntity(nickname, email, photo);
    }

    public void updateNicknameAndPhoto(String nickname, String photo) {
        this.nickname = nickname;
        this.photo = photo;
    }
}
