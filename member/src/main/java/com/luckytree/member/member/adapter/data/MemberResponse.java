package com.luckytree.member.member.adapter.data;

import com.luckytree.member.member.adapter.jpa.MemberEntity;
import com.luckytree.member.member.domain.Member;
import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MemberResponse {

    private String nickname;

    private String email;

    private String photo;

    public MemberResponse(MemberEntity memberEntity) {
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.photo = memberEntity.getPhoto();
    }

    public MemberResponse(Member member) {
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