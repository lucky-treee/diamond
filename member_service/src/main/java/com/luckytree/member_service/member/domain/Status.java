package com.luckytree.member_service.member.domain;

import com.luckytree.member_service.common.advice.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Status {
    NORMAL,
    DORMANT,
    LEAVE;

    public void isAlreadyDeleted(long memberId) {
        if(this == LEAVE) {
            throw new BadRequestException("이미 탈퇴된 회원 ID입니다.");
        }
    }
}
