package com.pongchi.glimelight.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pongchi.glimelight.api.v1.dto.ResponseDto;
import com.pongchi.glimelight.api.v1.dto.ResponsesDto;
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

    @ExceptionHandler({ CustomExceptions.class })
    private ResponseEntity<?> handleCustomExceptions(CustomExceptions ex) {
        return ResponsesDto.createResponsesEntity(
            ex.getResponseCode(),
            ex.getResults()
        );
    }

    @ExceptionHandler({ Exception.class })
    private ResponseEntity<?> handleServerException(Exception ex) {
        System.out.println(ex);
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

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    private ResponseEntity<?> notSupportRequestMethodException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.NOT_ALLOWED_METHOD,
            null
        );
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    private ResponseEntity<?> illegalArgumentException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.FORMAT_ERROR,
            null
        );
    }
}
