package com.pongchi.glimelight.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    // 성공
    SUCCESS(200, "OK"),

    //400 BAD REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),
    NOT_FOUND_PARAMETER(400, "필요한 파라미터 값을 확인해주세요."),

    // 401 Un-Authorization
    UNAUTHENTICATION(401, "로그인을 먼저 해주세요."),
    UNAUTHENTICATION_FAIL(401, "로그인 실패."),

    // 403 FORBIDDEN
    FORBIDDEN_REQUEST(403, "Forbidden."),

    // 404 NOT FOUND
    INVALID_PATH(404, "잘못된 경로입니다."),
    NOT_FOUND_MEMBER(404, "멤버를 찾을 수 없습니다."),
    NOT_FOUND_POST(404, "게시글을 찾을 수 없습니다."),
    NOT_FOUND_COMMENT(404, "댓글을 찾을 수 없습니다."),
    NOT_FOUND_LIKE(404, "좋아요를 찾을 수 없습니다."),
    NOT_FOUND_SUBSCRIBE(404, "구독을 찾을 수 없습니다."),

    // 405 Method Not Allowed
    NOT_ALLOWED_METHOD(405, "해당 요청의 메소드를 지원하지 않습니다."),

    // 409 CONFLICT
    CONCLICT_MEMBER(409, "중복된 이메일로 가입된 회원이 있습니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(
        500, "서버 에러입니다.");
    
    private final int status;
    private final String message;
}