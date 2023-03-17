package com.luckytree.shop_service.common.advice;

import com.luckytree.shop_service.common.exceptions.BadRequestException;
import com.luckytree.shop_service.common.exceptions.InternalServerErrorException;
import com.luckytree.shop_service.common.exceptions.NotFoundException;
import com.luckytree.shop_service.common.exceptions.UnAuthorizedException;
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestExceptionHandler(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> internalServerErrorExceptionHandler(InternalServerErrorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> unAuthorizedExceptionHandler(UnAuthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
