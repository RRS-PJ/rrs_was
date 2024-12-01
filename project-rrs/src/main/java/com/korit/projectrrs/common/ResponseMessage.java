package com.korit.projectrrs.common;

public class ResponseMessage {
    // 성공 및 일반 메시지
    public static final String SUCCESS = "Success"; // 성공 시 반환 메시지
    public static final String VALIDATION_FAIL = "Validation failed."; // 유효성 검사 실패 시 반환 메시지
    public static final String DATABASE_ERROR = "Database error."; // 데이터베이스 에러 시 반환 메시지
    public static final String INTERNAL_SERVER_ERROR = "Internal server error."; // 서버 내부 오류

    // 사용자 이름 관련 메시지
    public static final String INVALID_USER_NAME = "Invalid user name format."; // 유효하지 않은 사용자 이름 형식

    // 사용자 ID 관련 메시지
    public static final String INVALID_USER_ID = "Invalid user ID format."; // 유효하지 않은 사용자 ID 형식
    public static final String EXIST_USER_ID = "User already exists."; // 사용자가 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_USER_ID = "User does not exist."; // 사용자가 존재하지 않을 때 반환 메시지

    // 비밀번호 관련 메시지
    public static final String INVALID_USER_PASSWORD = "Invalid password format."; // 유효하지 않은 비밀번호 형식
    public static final String INVALID_CONFIRM_PASSWORD = "Password confirmation does not match."; // 비밀번호 확인이 일치하지 않을 때

    // 사용자 닉네임 관련 메시지
    public static final String EXIST_USER_NICKNAME = "User nickname already exists."; // 사용자의 닉네임이 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_USER_NICKNAME = "User nickname does not exist."; // 사용자가 닉네임이 존재하지 않을 때 반환 메시지
    public static final String INVALID_USER_NICKNAME = "Invalid user nickname format."; // 유효하지 않은 사용자 닉네임 형식

    // 사용자 이메일 관련 메시지
    public static final String EXIST_USER_EMAIL = "User email already exists."; // 사용자 이메일이 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_USER_EMAIL = "User email does not exist."; // 사용자 이메일이 존재하지 않을 때 반환 메시지
    public static final String INVALID_USER_EMAIL = "Invalid email format."; // 유효하지 않은 이메일 형식

    // 로그인 및 인증 관련 메시지
    public static final String SIGN_IN_FAIL = "Sign in failed."; // 로그인 실패 시 반환 메시지
    public static final String AUTHENTICATION_FAIL = "Authentication failed."; // 인증 실패 시 반환 메시지
    public static final String NOT_MATCH_USER_ID = "UserId does not match."; // 아이디 불일치 시 반환 메시지
    public static final String NOT_MATCH_PASSWORD = "Password does not match."; // 비밀번호 불일치 시 반환 메시지
    public static final String NO_PERMISSION = "No permission."; // 권한이 없을 때 반환 메시지

    // 사용자 주소 관련 메시지
    public static final String INVALID_USER_ADDRESS = "Invalid user address format."; // 유효하지 않은 사용자 주소 형식
    public static final String INVALID_USER_ADDRESS_DETAIL = "Invalid user address detail format."; // 유효하지 않은 사용자 상세주소 형식

    // 사용자 프로필 관련 메시지
    public static final String INVALID_USER_PROFILE = "Invalid user profile format."; // 유효하지 않은 사용자 프로필 형식

    // 게시글 관련 메시지
    public static final String EXIST_POST = "Post already exists."; // 게시글이 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_POST = "Post does not exist."; // 게시글이 존재하지 않을 때 반환 메시지

    // 댓글 관련 메시지
    public static final String EXIST_COMMENT = "Comment already exists."; // 댓글이 이미 존재할 때 반환 메시지
    public static final String NOT_EXIST_COMMENT = "Comment does not exist."; // 댓글이 존재하지 않을 때 반환 메시지

    // 로그인 및 인증 관련 메시지
    public static final String EMAIL_AUTH_FAIL = "Email authentication failed."; // 이메일 인증 실패
    public static final String PASSWORD_MISMATCH = "Password mismatch."; // 비밀번호 불일치
    public static final String TOKEN_EXPIRED = "Token has expired."; // 토큰 만료
    public static final String INVALID_TOKEN = "Invalid token."; // 잘못된 토큰
    public static final String ACCESS_DENIED = "Access denied."; // 접근 거부

    // 비밀번호 관련 메시지
    public static final String INVALID_PASSWORD_FORMAT = "Invalid password format."; // 잘못된 비밀번호 형식
    public static final String PASSWORD_TOO_WEAK = "Password is too weak."; // 비밀번호가 너무 약함
    public static final String PASSWORD_TOO_SHORT = "Password is too short."; // 비밀번호가 너무 짧음
    public static final String PASSWORD_TOO_LONG = "Password is too long."; // 비밀번호가 너무 김
    public static final String PASSWORD_REQUIRES_NUMBER = "Password must contain a number."; // 숫자가 포함되어야 함
    public static final String PASSWORD_REQUIRES_SPECIAL_CHAR = "Password must contain a special character."; // 특수문자가 포함되어야 함

    // 사용자 휴대폰 관련 메시지
    public static final String EXIST_USER_PHONE = "Phone already exists.";
    public static final String INVALID_USER_PHONE = "Invalid user phone number format."; // 유효하지 않은 사용자 전화번호 형식

    // 이메일 관련 메시지
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

    // 강아지 프로필 유효성 검사
    public static final String INVALID_PET_NAME = "Invalid pet name format."; // 유효하지 않은 강아지 이름 형식
    public static final String INVALID_PET_GENDER = "Invalid pet gender format."; // 유효하지 않은 강아지 성별 형식
    public static final String INVALID_PET_BIRTH_DATE = "Invalid pet birth date format."; // 유효하지 않은 강아지 생일 형식
    public static final String INVALID_PET_WEIGHT = "Invalid pet wight format."; // 유효하지 않은 강아지 몸무게 형식
    public static final String INVALID_PET_PROFILE = "Invalid pet profile format."; // 유효하지 않은 강아지 프로필 형식
    public static final String INVALID_PET_NEUTRALITY_YN = "Invalid pet neutrality format."; // 유효하지 않은 강아지 중성화 여부 형식

    // 존재하지 않는 강아지 id
    public static final String NOT_EXIST_PET_ID = "Pet does not exist."; // 강아지가 존재하지 않을 때 반환 메시지

    // 산책기록 유효성 검사
    public static final String INVALID_WALKING_RECORD_WEATHER_STATE = "Invalid Weather input."; // 유효하지 않은 산책기록 날씨 형식
    public static final String INVALID_WALKING_RECORD_DISTANCE = "Invalid Distance input."; // 유효하지 않은 산책기록 거리 형식
    public static final String INVALID_WALKING_RECORD_TIME = "Invalid hours or minutes input."; // 유효하지 않은 산책기록 산책시간 형식
    public static final String INVALID_WALKING_RECORD_CREATE_AT = "Invalid walking record createAt format."; // 유효하지 않은 산책기록 작성일 형식
    public static final String TIME_IN_FUTURE_NOT_ALLOWED = "Future time cannot be selected."; // 미래 시간 선택 불가

    // 존재하지 않는 산책기록 id
    public static final String NOT_EXIST_WALKING_RECORD_ID = "Walking Record does not exist."; // 산책기록이 존재하지 않을 때 반환 메시지
}
