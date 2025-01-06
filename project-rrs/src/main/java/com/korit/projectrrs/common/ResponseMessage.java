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

    // Todo 관련 메세지
    public static final String NOT_EXIST_TODO = "This todo is not exist"; // TODO가 존재하지 않음
    public static final String TODO_TOO_LONG = "Todo Content is too long."; // Todo 내용이 너무 김
    public static final String TODO_IS_EMPTY = "Todo Content is empty."; // Todo 내용이 없음
    public static final String NOT_MATCH_TODO_ID = "Todo ID is not matched";
    public static final String TODO_NOT_EXIST_STATUS = "This todo status is not exist";

    // 고객센터 관련 메세지
    public static final String NOT_EXIST_CUSTOMER_SUPPORT = "Customer support post is not exist."; // 고객센터 내용이 없음
    public static final String CS_STATUS_NOT_EXIST = "This CS status is not exist";
    public static final String CS_TITLE_PROBLEM = "This title got problem with validation";
    public static final String CS_CONTENT_PROBLEM = "This content got problem with validation";

    // 리뷰 관련 메세지
    public static final String REVIEW_TOO_lONG =  "Review Content is too long.";
    public static final String REVIEW_SCORE_NUMBER_VALIDATION =  "The review score is too long or too short";
    public static final String REVIEW_NO_OVERLAP = "Review cant not be overlapped";

    // 댕시터 관련 메세지
    public static final String NOT_EXIST_PROVIDER_ID = "Provider is not exist";
    public static final String NOT_REGISTERED_PROVIDER = "Provider is not registered";

    // 첨부파일 관련 메세지
    public static final String FILE_UPLOAD_FAILED = "File upload is failed";
    public static final String FILE_DELETE_FAILED = "File delete is failed";
    public static final String FILE_NOT_FOUND = "This file is not found";
    public static final String NOT_EXIST_CUSTOMER_SUPPORT_ATTACHMENT = "Customer support attachment is not exist.";

    // 커뮤니티 관련 메시지
    public static final String COMMUNITY_CREATED_SUCCESSFULLY = "Community created successfully."; // 커뮤니티 생성 성공
    public static final String COMMUNITY_UPDATED_SUCCESSFULLY = "Community updated successfully."; // 커뮤니티 업데이트 성공
    public static final String COMMUNITY_DELETED_SUCCESSFULLY = "Community deleted successfully."; // 커뮤니티 삭제 성공
    public static final String COMMUNITY_FETCHED_SUCCESSFULLY = "Community fetched successfully."; // 커뮤니티 조회 성공
    public static final String LIKE_TOGGLE_SUCCESS = "Like toggled successfully."; // 좋아요 상태 변경 성공
    public static final String COMMUNITY_NOT_FOUND = "Community not found."; // 커뮤니티를 찾을 수 없음
    public static final String NOT_EXIST_COMMUNITY = "The community does not exist."; // 존재하지 않는 커뮤니티

    // 권한 관련 메시지
    public static final String NOT_AUTHORIZED_TO_CREATE = "You do not have permission to create this community."; // 생성 권한 부족
    public static final String NOT_AUTHORIZED_TO_UPDATE = "You do not have permission to update this community."; // 업데이트 권한 부족
    public static final String NOT_AUTHORIZED_TO_DELETE = "You do not have permission to delete this community."; // 삭제 권한 부족
    public static final String NOT_AUTHORIZED_TO_TOGGLE_LIKE = "You cannot like your own community."; // 좋아요 권한 부족

    // 댓글 관련 메시지
    public static final String INVALID_COMMENT_CONTENT = "Comment content is invalid."; // 유효하지 않은 댓글 내용
    public static final String COMMENT_NOT_BELONG_TO_COMMUNITY = "Comment does not belong to the specified community."; // 커뮤니티에 속하지 않은 댓글
    public static final String NOT_AUTHORIZED_TO_UPDATE_COMMENT = "You do not have permission to update this comment."; // 댓글 업데이트 권한 부족
    public static final String NOT_AUTHORIZED_TO_DELETE_COMMENT = "You do not have permission to delete this comment."; // 댓글 삭제 권한 부족
    public static final String COMMENT_CREATED_SUCCESSFULLY = "Comment created successfully.";
    public static final String COMMENT_UPDATED_SUCCESSFULLY = "Comment updated successfully.";
    public static final String COMMENT_DELETED_SUCCESSFULLY = "Comment deleted successfully.";
    public static final String COMMENT_FETCHED_SUCCESSFULLY = "Comments fetched successfully.";

    // 인증 및 로그인 관련 메시지
    public static final String USER_NOT_AUTHENTICATED = "User is not authenticated. Please log in."; // 인증 실패
    public static final String USER_NOT_FOUND = "User not found."; // 사용자 없음
    public static final String NOT_AUTHORIZED_TO_CREATE_COMMENT = "You do not have permission to create this comment.";

    // 공지사항 관련 메시지
    public static final String NOT_FOUND_ANNOUNCEMENT = "Announcement not found."; // 공지사항을 찾을 수 없음
    public static final String NOT_EXIST_PET_ID = "Pet ID does not exist."; // 펫 ID가 존재하지 않을 때
    public static final String INVALID_PET_GENDER = "Invalid pet gender format."; // 잘못된 펫 성별 형식
    public static final String INVALID_PET_BIRTH_DATE = "Invalid pet birth date format."; // 잘못된 펫 생일 형식
    public static final String INVALID_PET_WEIGHT = "Invalid pet weight format."; // 잘못된 펫 무게 형식
    public static final String INVALID_PET_PROFILE = "Invalid pet profile format."; // 잘못된 펫 프로필 형식
    public static final String INVALID_PET_NEUTRALITY_YN = "Invalid pet neutrality (Y/N) format."; // 잘못된 중성화 여부 형식
    public static final String INVALID_PET_NAME = "Invalid pet name format."; // 잘못된 펫 이름 형식
    public static final String RECORD_CREATED_SUCCESSFULLY = "Health record created successfully."; // 건강 기록 생성 성공
    public static final String RECORD_NOT_FOUND = "Health record not found."; // 건강 기록을 찾을 수 없음
    public static final String NOT_EXIST_AVAILABLEDATE_ID = "Available date ID does not exist.";
    public static final String NOT_FOUND_EVENT = "The requested event could not be found.";
    public static final String NOT_FOUND_USAGE_GUIDE = "The requested usage guide could not be found.";

    public static final String RECORD_CREATION_FAILED = "Failed to create health record."; // 건강 기록 생성 실패
    public static final String RECORD_UPDATED_SUCCESSFULLY = "Health record updated successfully."; // 건강 기록 업데이트 성공
    public static final String RECORD_DELETED_SUCCESSFULLY = "Health record deleted successfully."; // 건강 기록 삭제 성공
    public static final String RECORDS_NOT_FOUND = "No health records found."; // 건강 기록을 찾을 수 없음
    public static final String RECORD_FETCHED_SUCCESSFULLY = "Health record fetched successfully."; // 건강 기록 조회 성공

    public static final String PET_INFO_NOT_FOUND = "Pet information not found. Please add pet information.";
    public static final String INVALID_PET_AGE = "Invalid pet age format."; // 잘못된 펫 나이 형식
    public static final String INVALID_WEIGHT = "Invalid weight format."; // 잘못된 무게 형식
    public static final String NOT_AUTHORIZED = "You are not authorized to perform this operation."; // 이 작업을 수행할 권한이 없음

    // 예약 관련 메세지
    public static final String START_DATE_CANNOT_BE_IN_PAST = "Start date cannot be in the past.";
    public static final String INVALID_DATE_TOO_LATE = "Start date or end date cannot be more than 30 days from the current date.";
    public static final String INVALID_DATE_RANGE = "End date cannot be before start date.";
    public static final String DATE_RANGE_TOO_LONG = "The reservation duration cannot exceed 7 days.";
    public static final String MINIMUM_ONE_DAY_RESERVATION = "Reservation must be at least one day.";

    public static final String NOT_EXIST_RESERVATION = "Reservation is not exist";
    public static final String RESERVATION_MEMO_TOO_LONG = "The reservation's memo is too long";
    public static final String RESERVATION_VALIDATION_ERROR = "date of reservation's validationError";
    public static final String RESERVATION_ALREADY_EXISTS = "This provider already has a reservation";
    public static final String INVALIDATED_RESERVATION_STATUS = "The reservation status is invalid";
    public static final String RESERVATION_IS_NOT_COMPLETED = "This reservation is not completed";
    public static final String UNKNOWN_STATUS = "This is Unknown ReservationStatus";

    // 산책기록 유효성 검사
    public static final String INVALID_WALKING_RECORD_WEATHER_STATE = "Invalid Weather format."; // 유효하지 않은 산책기록 날씨 형식
    public static final String INVALID_WALKING_RECORD_DISTANCE = "Invalid Distance format."; // 유효하지 않은 산책기록 거리 형식
    public static final String INVALID_WALKING_RECORD_TIME = "Invalid hours or minutes format."; // 유효하지 않은 산책기록 산책시간 형식
    public static final String INVALID_WALKING_RECORD_CREATE_AT = "Invalid walking record createAt format."; // 유효하지 않은 산책기록 작성일 형식
    public static final String TIME_IN_FUTURE_NOT_ALLOWED = "Future time cannot be selected."; // 미래 날짜 선택 불가
    public static final String INVALID_FILE = "Invalid file format."; // 유효하지 않은 파일 형식
    public static final String FILE_UPLOAD_ERROR = "An error occurred while uploading the file."; // 파일 업로드 에러

    // 존재하지 않는 산책기록 id
    public static final String NOT_EXIST_WALKING_RECORD_ID = "Walking Record does not exist."; // 산책기록이 존재하지 않을 때 반환 메시지

    // 사용자 정보 수정 관련 메시지
    public static final String NO_MODIFIED_VALUES = "No changes detected in the provided values."; // 수정 값이 없을 때 반환 메시지
    public static final String NOT_EXIST_REVIEW = "This review is not exist";
}
