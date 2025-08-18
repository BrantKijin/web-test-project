package com.example.webtestproject.common.exception.status;

import lombok.Getter;

@Getter
public enum XperpHttpStatus {
	// B2C 에러 메시지 정의 400번대

	BAD_REQUEST(400, "잘못된 요청입니다. 입력값을 확인해 주세요."),
	UNAUTHORIZED(401, "인증 정보가 유효하지 않거나 만료되었습니다. 다시 로그인 해 주세요."),
	PAYMENT_REQUIRED(402, "결제가 필요합니다. 결제 후 다시 시도해 주세요."),
	FORBIDDEN(403, "접근 권한이 없습니다. 관리자에게 문의해 주세요."),
	NOT_FOUND(404, "요청하신 정보를 찾을 수 없습니다."),
	METHOD_NOT_ALLOWED(405, "지원되지 않는 요청 방식입니다. API 명세를 확인해 주세요."),
	NOT_ACCEPTABLE(406, "요청한 데이터 형식을 처리할 수 없습니다. 올바른 형식으로 요청해 주세요."),
	REQUEST_TIMEOUT(408, "요청 시간이 초과되었습니다. 다시 시도해 주세요."),
	CONFLICT(409, "요청이 충돌되었습니다. 이미 존재하는 데이터인지 확인해 주세요."),
	GONE(410, "요청한 데이터가 존재하지 않거나 삭제되었습니다."),
	PAYLOAD_TOO_LARGE(413, "요청 데이터가 너무 큽니다. 허용 범위를 초과하지 않도록 해 주세요."),
	UNSUPPORTED_MEDIA_TYPE(415, "요청한 데이터 형식이 지원되지 않습니다. JSON 형식인지 확인해 주세요."),
	TOO_MANY_REQUESTS(429, "너무 많은 요청이 발생했습니다. 잠시 후 다시 시도해 주세요."),

	//1000 번대 부터 XPERP 전용 커스텀 에러 메시지
	MULTI_LOGIN(1000, "세션 만료 또는 다른 장치에서 접속하여 로그아웃되었습니다. 다시 로그인 해 주세요"),
	ID_PASSWD_CHECK(1001, "아이디 또는 비밀번호를 확인해주세요"),
	NOT_FOUND_USER(1002, "일치하는 아이디가 없습니다.아이디를 다시 확인해 주세요"),
	NOT_REFRESH_TOKEN(1003, "Refresh 토큰이 존재하지 않습니다"),
	NOT_FOUND_DNAJI(1004, "단지정보를 찾을 수 없습니다"),
	FILE_SIZE_EXCEEDED(1005, "파일 사이즈가 초과되었습니다."),
	FILE_COUNT_EXCEED(1006, "파일 갯수가 초과되었습니다."),
	INVALID_FILE_NAME(1007, "파일 이름이 유효하지 않습니다."),
	UNSUPPORTED_FILE_FORMAT(1008, "허용되지 않는 파일 형식입니다."),
	INVALID_REQUEST_CONDITION(1009, "요청조건에 맞지않습니다."),
	HIDDEN_POST_FLAG(1010, "가림 처리된 문의입니다."),
	DO_NOT_ACCESS(1011, "접근 권한이 없습니다"),
	S3_PRESIGND_ERROR(1012, "프리사인 URL 생성 중 오류가 발생하였습니다."),
	S3_UPLOAD_ERROR(1013, "S3 업로드 중에 오류가 발생하였습니다."),
	NOT_USED_ID(1014, "입력하신 계정은 휴면,퇴직,미승인 회원입니다.소속 전산 업체에 문의해 주세요."),
	ALREADY_AGREED_EXCEPTION(1015,"이미 동의한 내역이 존재합니다.");
	private final int code;
	private final String message;

	XperpHttpStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static XperpHttpStatus valueOf(int statusCode) {
		for (XperpHttpStatus status : values()) {
			if (status.code == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("정의되지 않은 상태 코드입니다: " + statusCode);
	}
}
