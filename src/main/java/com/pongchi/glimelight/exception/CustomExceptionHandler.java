package com.pongchi.glimelight.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pongchi.glimelight.api.v1.dto.ResponseDto;
import com.pongchi.glimelight.common.ResponseCode;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<?> handleCustomException(CustomException ex) {
        return ResponseDto.createResponseEntity(
            ex.getResponseCode(),
            null
        );
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<?> handleServerException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.INTERNAL_SERVER_ERROR,
            null
        );
    }
}
