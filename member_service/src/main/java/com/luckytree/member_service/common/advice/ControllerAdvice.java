package com.luckytree.member_service.common.advice;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Object> noContentExceptionHandler(NoContentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestExceptionHandler(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> nullPointerException(InternalServerErrorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
