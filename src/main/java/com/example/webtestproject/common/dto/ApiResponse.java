package com.example.webtestproject.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	private ApiStatus status;
	private String message;
	private T data;
	private Integer errorCode; // 실패 시만 포함되는 필드

	public ApiResponse(ApiStatus status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(ApiStatus status, String message, T data, Integer errorCode) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.errorCode = errorCode;
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(ApiStatus.SUCCESS, null, data);
	}

	public static ApiResponse<String> error(String message) {
		return new ApiResponse<>(ApiStatus.ERROR, message, null, 400);
	}

	public static ApiResponse<String> error(String message, int errorCode) {
		return new ApiResponse<>(ApiStatus.ERROR, message, null, errorCode);
	}
}
