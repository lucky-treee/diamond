package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.member.domain.Photo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupDto {

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private Photo photo;
}
