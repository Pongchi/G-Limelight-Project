package com.pongchi.glimelight.exception;

import java.util.List;

import com.pongchi.glimelight.common.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomExceptions extends RuntimeException {
    private final ResponseCode responseCode;
    private final List<String> results;
}