package com.luckytree.member_service.member.adapter.data;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class AuthenticateResult {

    @NotNull
    private String email;

    @NotNull
    private boolean isMember;

    public AuthenticateResult(String email, boolean isMember) {
        this.email = email;
        this.isMember = isMember;
    }
}
