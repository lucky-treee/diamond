package com.luckytree.member_service.member.adapter.data;

import com.luckytree.member_service.member.domain.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WithdrawalMemberDto {

    @NotBlank
    private String email;
    @NotBlank
    private Status status;
}