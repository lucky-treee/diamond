package com.luckytree.member_service.member.adapter.out;

import com.luckytree.member_service.common.dto.ResultResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.NoContentException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResultResponse notFoundExceptionHandler(NotFoundException e) {
        return new ResultResponse<>(-10404, e.getMessage());
    }
    @ExceptionHandler(NoContentException.class)
    public ResultResponse noContentExceptionHandler(NoContentException e) {
        return new ResultResponse<>(-10204, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResultResponse badRequestExceptionHandler(BadRequestException e) {
        return new ResultResponse<>(-10400, e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultResponse nullPointerException(NullPointerException e) {
        return new ResultResponse<>(-10500, e.getMessage());
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResultResponse notImplementedExceptionHandler(NotImplementedException e) {
        return new ResultResponse<>(-10501, e.getMessage());
    }
}
