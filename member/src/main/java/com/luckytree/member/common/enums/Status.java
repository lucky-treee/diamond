package com.luckytree.member.common.enums;

import com.luckytree.member.common.advice.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Status {
    NORMAL,
    DORMANT,
    LEAVE,
    ;

    public void isAlreadyDeleted() {
        if(this == LEAVE) {
            throw new BadRequestException("이미 탈퇴된 회원 ID입니다.");
        }
    }
}
