package com.example.webtestproject.common.dto.external.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record JpaToIdcDataResponse<T>(

	@Schema(description = "상태")
	String status,

	@Schema(description = "메시지")
	String message,

	@Schema(description = "데이터")
	T data

) {}