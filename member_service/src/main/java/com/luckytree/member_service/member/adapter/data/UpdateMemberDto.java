package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.member.domain.Photo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateMemberDto {

    @NotBlank
    private String email;
    @NotBlank
    private String nickname;
    @NotBlank
    private Photo photo;
}
