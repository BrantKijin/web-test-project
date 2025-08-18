package com.example.webtestproject.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class XperpCustomException extends RuntimeException {
	private final int errorCode;

	public XperpCustomException(String message) {
		super(message);
		this.errorCode = HttpStatus.BAD_REQUEST.value(); // 기본 에러는 400으로
	}

	// 에러 코드와 메시지를 함께 지정하는 생성자
	public XperpCustomException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public XperpCustomException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = HttpStatus.BAD_REQUEST.value();
	}

	public XperpCustomException(Throwable cause) {
		super(cause);
		this.errorCode = HttpStatus.BAD_REQUEST.value();
	}
}
