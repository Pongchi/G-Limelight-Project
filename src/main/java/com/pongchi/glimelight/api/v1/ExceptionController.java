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
    
    @GetMapping("/api/v1/exception/authentication-fail")
    public ResponseEntity<?> authenticationFail() {
        return createResponseEntity(
            ResponseCode.UNAUTHENTICATION,
            null
        );
    }

    @GetMapping("/api/v1/exception/authorization-fail")
    public ResponseEntity<?> authorizationFail() {
        return createResponseEntity(
            ResponseCode.FORBIDDEN_REQUEST,
            null
        );
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return createResponseEntity(ResponseCode.SUCCESS, "test");
    }
    
}
