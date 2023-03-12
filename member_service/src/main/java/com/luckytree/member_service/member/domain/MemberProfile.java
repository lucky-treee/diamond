package com.luckytree.member_service.member.domain;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MemberProfile {

    private String nickname;

    private String email;

    private Photo photo;

    public MemberProfile(MemberEntity memberEntity) {
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.photo = memberEntity.getPhoto();
    }

    public MemberProfile(Member member) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.photo = member.getPhoto();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePhoto(Photo photo) {
        this.photo = photo;
    }
}