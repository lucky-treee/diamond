package com.luckytree.member.member.adapter.data.member;

import com.luckytree.member.member.domain.member.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import luckytree.poom.core.jwt.Role;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class GetMemberResponse {

    private Long id;

    private Role role;

    public GetMemberResponse(Member member) {
        this.id = member.getId();
        this.role = member.getRole();
    }
}
