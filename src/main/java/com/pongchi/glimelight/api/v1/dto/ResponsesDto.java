package com.pongchi.glimelight.api.v1.dto;

import org.springframework.http.ResponseEntity;

import com.pongchi.glimelight.common.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponsesDto {

    private String message;
    private Object[] result;

    public static ResponseEntity<ResponsesDto> createResponseEntity(ResponseCode code, Object[] result) {
        ResponsesDto responseDto = new ResponsesDto(code.getMessage(), result);
        return ResponseEntity.status(code.getStatus()).body(responseDto);
    }
}
