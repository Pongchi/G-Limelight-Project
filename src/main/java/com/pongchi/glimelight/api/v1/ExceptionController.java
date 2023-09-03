package com.pongchi.glimelight.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.common.ResponseCode;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ExceptionController {

    @GetMapping("/exception/not-found")
    public ResponseEntity<?> notFound() {
        return createResponseEntity(
            ResponseCode.INVALID_PATH,
            null
        );
    }
    
    @GetMapping("/exception/authentication-fail")
    public ResponseEntity<?> authenticationFail() {
        return createResponseEntity(
            ResponseCode.UNAUTHENTICATION_FAIL,
            null
        );
    }

    @GetMapping("/exception/authorization-fail")
    public ResponseEntity<?> authorizationFail() {
        return createResponseEntity(
            ResponseCode.FORBIDDEN_REQUEST,
            null
        );
    }

    @GetMapping("/exception/interval-server-error")
    public ResponseEntity<?> intervalServerError() {
        return createResponseEntity(
            ResponseCode.INTERNAL_SERVER_ERROR,
            null
        );
    }
}
