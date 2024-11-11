package com.korit.projectrrs.common;

public class ResponseMessage {

    // 성공 및 일반 메시지
    public static final String SUCCESS = "Success"; // 성공 시 반환 메시지
    public static final String VALIDATION_FAIL = "Validation failed."; // 유효성 검사 실패 시 반환 메시지
    public static final String DATABASE_ERROR = "Database error."; // 데이터베이스 에러 시 반환 메시지

    // 사용자 관련 메시지
    public static final String EXIST_USER = "User already exists."; // 사용자가 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_USER = "User does not exist."; // 사용자가 존재하지 않을 때 반환 메시지
    public static final String DUPLICATED_USER_ID = "Duplicated user ID."; // 사용자 ID 중복 시 반환 메시지

    // 로그인 및 인증 관련 메시지
    public static final String SIGN_IN_FAIL = "Sign in failed."; // 로그인 실패 시 반환 메시지
    public static final String AUTHENTICATION_FAIL = "Authentication failed."; // 인증 실패 시 반환 메시지
    public static final String NOT_MATCH_PASSWORD = "Password does not match."; // 비밀번호 불일치 시 반환 메시지
    public static final String NO_PERMISSION = "No permission."; // 권한이 없을 때 반환 메시지

    // 게시글 관련 메시지
    public static final String EXIST_POST = "Post already exists."; // 게시글이 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_POST = "Post does not exist."; // 게시글이 존재하지 않을 때 반환 메시지

    // 댓글 관련 메시지
    public static final String EXIST_COMMENT = "Comment already exists."; // 댓글이 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_COMMENT = "Comment does not exist."; // 댓글이 존재하지 않을 때 반환 메시지

    // 기타 메시지
    public static final String TOKEN_CREATE_FAIL = "Token creation failed."; // 토큰 생성 실패 시 반환 메시지
    public static final String MESSAGE_SEND_FAIL = "Failed to send authentication number."; // 인증 번호 전송 실패 시 반환 메시지

    // 추가
    public static final String INVALID_NAME = "유효하지 않는 이름입니다.";
    public static final String INVALID_USER_ID = "유효하지 않는 아이디입니다.";
    public static final String INVALID_PASSWORD = "유효하지 않는 비밀번호입니다.";
    public static final String INVALID_NICKNAME = "유효하지 않는 닉네임입니다.";
    public static final String INVALID_EMAIL = "유효하지 않는 이메일입니다.";
    public static final String INVALID_PHONE = "유효하지 않는 전화번호입니다.";
    public static final String INVALID_ADDRESS = "유효하지 않는 번호입니다.";
    public static final String INVALID_IMAGE_URL = "유효하지 않는 이미지 형식입니다.";


}