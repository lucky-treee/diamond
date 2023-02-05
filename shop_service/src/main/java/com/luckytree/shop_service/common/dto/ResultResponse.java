package com.luckytree.shop_service.common.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResultResponse<T> {

    private int status;     // http 상태값
    private int code;       // 에러 코드

    private String message; // 메시지

    private T result;       // 전송 데이터

    public ResultResponse(T result) {
        this.status = HttpStatus.OK.value();
        this.message = "success";
        this.result = result;
    }

    public ResultResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }

    public ResultResponse(int code, String message) {
        this.status = HttpStatus.OK.value();
        this.code = code;
        this.message = message;
    }
}
