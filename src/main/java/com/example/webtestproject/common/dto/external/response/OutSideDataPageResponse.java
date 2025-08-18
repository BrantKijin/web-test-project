package com.example.webtestproject.common.dto.external.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record OutSideDataPageResponse<T>(

	@Schema(description = "상태")
	String status,

	@Schema(description = "메시지")
	String message,

	@Schema(description = "페이지 데이터")
	JpaToIdcDataPageInfo<T> data

) {}