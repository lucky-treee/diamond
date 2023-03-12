package com.luckytree.member_service.common.advice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    private String message;
    private Object data;

    public NotFoundException(String message) {
        this.message = message;
        this.data = null;
    }

    public NotFoundException(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
