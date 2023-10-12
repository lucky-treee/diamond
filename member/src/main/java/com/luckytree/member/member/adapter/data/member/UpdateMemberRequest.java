package com.luckytree.member.member.adapter.data.member;

import com.luckytree.member.member.domain.member.MemberDetail;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateMemberRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String photo;

    public MemberDetail toMemberDetail(Long id) {
        return new MemberDetail(id, nickname, photo);
    }
}
