package com.pongchi.glimelight.exception;

import com.pongchi.glimelight.common.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final ResponseCode responseCode;
}
