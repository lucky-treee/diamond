package com.luckytree.shop_service.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@Schema(description = "샵 api 통신 결과정보")
public class ResultResponse<T> {

    @Schema(description = "http 상태값")
    private int status;     // http 상태값
    @Schema(description = "에러 코드")
    private int code;       // 에러 코드

    @Schema(description = "메세지")
    private String message; // 메시지

    @Schema(description = "전송 데이터")
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
