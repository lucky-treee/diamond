package com.luckytree.member_service.member.adapter.data;

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
}
