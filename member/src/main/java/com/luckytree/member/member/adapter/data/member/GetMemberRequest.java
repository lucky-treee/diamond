package com.luckytree.member.member.adapter.data.member;

import com.luckytree.member.member.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetMemberRequest {
    private Long id;

    private String email;

    public Member toDomain() {
        return new Member(id, email);
    }
}
