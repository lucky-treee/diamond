package com.luckytree.member_service.member.domain;

import com.luckytree.member_service.common.advice.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Status {
    NORMAL, DORMANT, LEAVE;

    public Status isMemberStatus(Status status) {
        if (status == LEAVE) {
            throw new BadRequestException("이미 탈퇴 처리된 회원입니다.");
        }
        return LEAVE;
    }
}
