package luckytree.poom.core.handler

import luckytree.poom.core.exceptions.BadRequestException
import luckytree.poom.core.exceptions.InternalServerErrorException
import luckytree.poom.core.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(e: NotFoundException): ResponseEntity<Any> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequestExceptionHandler(e: BadRequestException): ResponseEntity<Any> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun internalServerErrorHandler(e: InternalServerErrorException): ResponseEntity<Any> {
        return ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
