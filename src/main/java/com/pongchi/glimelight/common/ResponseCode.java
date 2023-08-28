package com.pongchi.glimelight.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    // 성공
    SUCCESS(200, "OK"),

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    NOT_FOUNDED_PARAMETER(400, "필요한 파라미터 값을 확인해주세요."),

    // 컨트롤러에 없는 경로
    INVALID_PATH(404, "잘못된 경로입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(
        500, "서버 에러입니다.");
    
    private final int status;
    private final String message;
}