package com.luckytree.member_service.member.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequest {

    @NotBlank
    @Size(max = 20)
    private String nickname;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String password;

    @Size(max = 50)
    private String photo;
}
