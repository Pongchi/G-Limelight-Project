package com.pongchi.glimelight.exception;

import java.security.SignatureException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler({ NoHandlerFoundException.class })
    private ResponseEntity<?> invalidPathException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.NOT_FOUND_PARAMETER,
            null
        );
    }

    @ExceptionHandler({ AccessDeniedException.class })
    private ResponseEntity<?> AccessDeniedException(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.FORBIDDEN_REQUEST,
            null
        );
    }

    @ExceptionHandler({ AuthenticationException.class })
    private ResponseEntity<?> AuthenticationExcpetion(Exception ex) {
        return ResponseDto.createResponseEntity(
            ResponseCode.UNAUTHORIZATION,
            null
        );
    }
}
