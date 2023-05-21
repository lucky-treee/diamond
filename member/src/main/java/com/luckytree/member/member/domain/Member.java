package com.luckytree.member.member.domain;


import com.luckytree.member.member.adapter.jpa.MemberEntity;
import lombok.Getter;
import luckytree.poom.core.enums.MemberStatus;

@Getter
public class Member {

    private String nickname;

    private String email;

    private MemberStatus status;

    private String photo;

    public Member(MemberEntity memberEntity) {
        this.email = memberEntity.getEmail();
        this.nickname = memberEntity.getNickname();
        this.photo = memberEntity.getPhoto();
        this.status = memberEntity.getStatus();
    }

    public Member(String nickname, String email, MemberStatus status, String photo) {
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
