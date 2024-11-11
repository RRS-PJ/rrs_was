package com.korit.projectrrs.common;

    public class ResponseMessage {

        // 성공 및 일반 메시지
        public static final String SUCCESS = "Success"; // 성공 시 반환 메시지
        public static final String VALIDATION_FAIL = "Validation failed."; // 유효성 검사 실패 시 반환 메시지
        public static final String INVALID_USER_ID = "Id Validation failed."; //
        public static final String DATABASE_ERROR = "Database error."; // 데이터베이스 에러 시 반환 메시지
        public static final String INTERNAL_SERVER_ERROR = "Internal server error."; // 서버 내부 오류

        // 존재 여부 관련 메시지
        public static final String EXIST_DATA = "Data already exists."; // 데이터가 이미 존재
        public static final String NOT_EXIST_DATA = "Data does not exist."; // 데이터가 존재하지 않음
        public static final String NOT_EXIST_USER = "User does not exist."; // 사용자 미존재
        public static final String NOT_EXIST_POST = "Post does not exist."; // 게시글 미존재

        // 중복 관련 메시지
        public static final String EXIST_USER = "User already exists."; // 사용자가 이미 존재할 때 반환 메시지
        public static final String DUPLICATED_USER_ID = "Duplicated user ID."; // 사용자 ID 중복
        public static final String DUPLICATED_USER_NAME = "Duplicated user name."; // 사용자 이름 중복
        public static final String DUPLICATED_EMAIL = "Duplicated email."; // 이메일 중복
        public static final String DUPLICATED_TEL_NUMBER = "Duplicated telephone number."; // 전화번호 중복
        public static final String DUPLICATED_NICKNAME = "Duplicated nickname."; // 닉네임 중복

        // 로그인 및 인증 관련 메시지
        public static final String SIGN_IN_FAIL = "Sign in failed."; // 로그인 실패
        public static final String AUTHENTICATION_FAIL = "Authentication failed."; // 인증 실패
        public static final String NO_PERMISSION = "No permission."; // 권한 없음
        public static final String EMAIL_AUTH_FAIL = "Email authentication failed."; // 이메일 인증 실패
        public static final String PASSWORD_MISMATCH = "Password mismatch."; // 비밀번호 불일치
        public static final String TOKEN_EXPIRED = "Token has expired."; // 토큰 만료
        public static final String INVALID_TOKEN = "Invalid token."; // 잘못된 토큰
        public static final String ACCESS_DENIED = "Access denied."; // 접근 거부

        // 비밀번호 관련 메시지
        public static final String NOT_MATCH_PASSWORD = "Password does not match."; // 비밀번호 불일치 시 반환 메시지
        public static final String INVALID_PASSWORD_FORMAT = "Invalid password format."; // 잘못된 비밀번호 형식
        public static final String PASSWORD_TOO_WEAK = "Password is too weak."; // 비밀번호가 너무 약함
        public static final String PASSWORD_TOO_SHORT = "Password is too short."; // 비밀번호가 너무 짧음
        public static final String PASSWORD_TOO_LONG = "Password is too long."; // 비밀번호가 너무 김
        public static final String PASSWORD_REQUIRES_NUMBER = "Password must contain a number."; // 숫자가 포함되어야 함
        public static final String PASSWORD_REQUIRES_SPECIAL_CHAR = "Password must contain a special character."; // 특수문자가 포함되어야 함

        // 이메일 관련 메시지
        public static final String INVALID_EMAIL = "Invalid email format."; // 잘못된 이메일 형식
        public static final String EMAIL_NOT_VERIFIED = "Email not verified."; // 이메일 미인증
        public static final String EMAIL_VERIFIED = "Email already verified."; // 이메일 인증 완료됨
        public static final String EMAIL_VERIFICATION_FAILED = "Email verification failed."; // 이메일 인증 실패

        // 사용자 관련 메시지
        public static final String USERNAME_TOO_SHORT = "Username is too short."; // 사용자 이름이 너무 짧음
        public static final String USERNAME_TOO_LONG = "Username is too long."; // 사용자 이름이 너무 김
        public static final String USERNAME_CONTAINS_INVALID_CHARACTERS = "Username contains invalid characters."; // 사용자 이름에 유효하지 않은 문자가 포함됨
        public static final String USER_ID_TOO_SHORT = "User ID is too short."; // 사용자 ID가 너무 짧음
        public static final String USER_ID_TOO_LONG = "User ID is too long."; // 사용자 ID가 너무 김

        // 요청 및 데이터 관련 메시지
        public static final String BAD_REQUEST = "Bad request."; // 잘못된 요청
        public static final String UNPROCESSABLE_ENTITY = "Unprocessable entity."; // 처리할 수 없는 요청
        public static final String RESOURCE_NOT_FOUND = "Resource not found."; // 리소스 미존재

        // 토큰 및 인증 관련 메시지
        public static final String TOKEN_CREATE_FAIL = "Token creation failed."; // 토큰 생성 실패
        public static final String TOKEN_INVALID = "Invalid token."; // 잘못된 토큰
        public static final String MESSAGE_SEND_FAIL = "Failed to send authentication number."; // 인증 번호 전송 실패
        public static final String AUTHORIZATION_REQUIRED = "Authorization required."; // 인증 필요

        // 기타 메시지
        public static final String OPERATION_FAILED = "Operation failed."; // 작업 실패
        public static final String OPERATION_SUCCESS = "Operation successful."; // 작업 성공
        public static final String RESOURCE_ALREADY_EXISTS = "Resource already exists."; // 리소스 이미 존재
        public static final String DATABASE_CONNECTION_FAIL = "Database connection failed."; // 데이터베이스 연결 실패

        // URL 관련 메시지
        public static final String INVALID_IMAGE_URL = "Invalid image URL."; // 잘못된 이미지 URL
        public static final String IMAGE_FORMAT_NOT_SUPPORTED = "Image format not supported."; // 지원되지 않는 이미지 형식
        public static final String IMAGE_TOO_LARGE = "Image size is too large."; // 이미지 크기가 너무 큼

        // 파일 관련 메시지
        public static final String INVALID_IMAGE_FILE = "Invalid image file."; // 잘못된 이미지 파일
        public static final String UNSUPPORTED_IMAGE_FILE = "Unsupported image file type."; // 지원되지 않는 이미지 파일 형식
        public static final String FILE_TOO_LARGE = "File size is too large."; // 파일 크기가 너무 큼

        // 인증 관련 메시지
        public static final String IMAGE_AUTHORIZATION_FAIL = "Image authorization failed."; // 이미지 인증 실패

}