package com.pongchi.glimelight.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.common.ResponseCode;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;

@RestController
public class TestController {

    @GetMapping("/")
    public ResponseEntity<?> testIndex() {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            null
        );
    }

    @GetMapping("/api/v1/test")
    public ResponseEntity<?> test() {
        return createResponseEntity(
            ResponseCode.SUCCESS,
            null
        );
    }
}