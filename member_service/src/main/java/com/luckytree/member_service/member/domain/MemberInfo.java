package com.luckytree.member_service.member.domain;

import com.luckytree.member_service.member.adapter.persistence.MemberEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class MemberInfo {

    private String nickname;

    private String email;

    private Photo photo;

    public MemberInfo(MemberEntity memberEntity) {
        this.nickname = memberEntity.getNickname();
        this.email = memberEntity.getEmail();
        this.photo = memberEntity.getPhoto();
    }
}