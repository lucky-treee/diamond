package com.luckytree.member_service.member.adapter.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private final String code;

    @NotBlank
    @JsonProperty(value = "redirect_uri")
    private final String redirectUri;
}
