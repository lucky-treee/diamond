package com.luckytree.member.member.adapter.data.member;

import com.luckytree.member.member.adapter.jpa.MemberEntity;
import com.luckytree.member.member.domain.member.Member;
import lombok.*;
import luckytree.poom.core.enums.MemberStatus;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class MemberResponse {

    private Long id;

    private String nickname;

    private String email;

    private String photo;

    private MemberStatus status;

    private LocalDateTime updateAt;

    private LocalDateTime createAt;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.photo = member.getPhoto();
        this.status = member.getStatus();
        this.updateAt = member.getUpdateAt();
        this.createAt = member.getCreateAt();
    }
}
