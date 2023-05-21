package com.luckytree.member.member.adapter.data.member;

import com.luckytree.member.member.domain.member.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMemberRequest {

    @NotNull
    private String nickname;

    @NotNull
    private String email;

    private String photo;

    public Member toDomain() {
        return new Member(
                nickname,
                email,
                photo
        );
    }
}
