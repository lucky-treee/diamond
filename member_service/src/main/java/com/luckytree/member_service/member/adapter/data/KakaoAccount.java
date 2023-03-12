package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luckytree.member_service.common.advice.InternalServerErrorException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KakaoAccount {

    @JsonProperty(value = "has_email")
    private boolean hasEmail;

    @JsonProperty(value = "email_needs_agreement")
    private boolean emailNeedsAgreement;

    @JsonProperty(value = "is_email_valid")
    private boolean isEmailValid;

    @JsonProperty(value = "is_email_verified")
    private boolean isEmailVerified;

    private String email;

    @JsonIgnore
    public String getEmail() {
        if (email == null) {
            throw new InternalServerErrorException("회원의 이메일을 받지 못했습니다.");
        }
        return email;
    }
}
