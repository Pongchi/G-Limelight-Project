package com.pongchi.glimelight.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.pongchi.glimelight.api.v1.dto.ResponseDto;
import com.pongchi.glimelight.common.ResponseCode;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({ CustomException.class })
    private ResponseEntity<?> handleCustomException(CustomException ex) {
        return ResponseDto.createResponseEntity(
            ex.getResponseCode(),
            null
        );
    }

    @ExceptionHandler({ Exception.class })
    private ResponseEntity<?> handleServerException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.INTERNAL_SERVER_ERROR,
            null
        );
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    private ResponseEntity<?> notFoundedParameterException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.NOT_FOUND_PARAMETER,
            null
        );
    }

    @ExceptionHandler({ NoHandlerFoundException.class })
    private ResponseEntity<?> invalidPathException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.NOT_FOUND_PARAMETER,
            null
        );
    }
}
