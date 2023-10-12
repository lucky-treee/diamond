package com.luckytree.member.member.domain.member;

import lombok.Getter;
import luckytree.poom.core.enums.MemberStatus;

@Getter
public class MemberDetail {
    private Long id;

    private String nickname;

    private MemberStatus status;

    private String photo;

    public MemberDetail(Long id, String nickname, String photo) {
        this.id = id;
        this.nickname = nickname;
        this.status = MemberStatus.NORMAL;
        this.photo = photo;
    }
}
