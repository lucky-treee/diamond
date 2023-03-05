package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoAccount {

    @JsonProperty(value = "email_needs_agreement")
    private boolean emailNeedsAgreement;

    @JsonProperty(value = "is_email_valid")
    private boolean isEmailValid;

    @JsonProperty(value = "is_email_verified")
    private boolean isEmailVerified;

    private String email;
}
