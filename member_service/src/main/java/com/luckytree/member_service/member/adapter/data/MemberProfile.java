package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import com.luckytree.member_service.member.domain.Member;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MemberProfile {

    private String nickname;

    private String email;

    private String photo;

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

    public void updatePhoto(String photo) {
        this.photo = photo;
    }
}