package com.luckytree.member.member.domain.member;


import com.luckytree.member.member.adapter.jpa.MemberEntity;
import lombok.Getter;
import luckytree.poom.core.enums.MemberStatus;
import luckytree.poom.core.jwt.Role;

import java.time.LocalDateTime;

@Getter
public class Member {

    private Long id;

    private Role role;

    private String nickname;

    private String email;

    private MemberStatus status;

    private String photo;

    private LocalDateTime updateAt;

    private LocalDateTime createAt;

    public Member(MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.role = memberEntity.getRole();
        this.email = memberEntity.getEmail();
        this.nickname = memberEntity.getNickname();
        this.photo = memberEntity.getPhoto();
        this.status = memberEntity.getStatus();
        this.updateAt = memberEntity.getUpdateAt();
        this.createAt = memberEntity.getCreateAt();
    }

    public Member(String nickname, String email, String photo) {
        this.nickname = nickname;
        this.email = email;
        this.photo = photo;
    }

    public Member(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public void update(MemberDetail memberDetail) {
        this.nickname = memberDetail.getNickname();
        this.photo = memberDetail.getPhoto();
    }

    public void update(MemberStatus status) {
        this.status = status;
    }
}
