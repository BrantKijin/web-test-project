package com.example.webtestproject.common.dto.external.response;

import io.swagger.v3.oas.annotations.media.Schema;

//아파트아이 커뮤니티 게시판쪽 공통 Response
public record AptIDataHubResponse<T>(

	@Schema(description = "응답코드(정상:0)", example = "20204")
	String code,

	@Schema(description = "응답메시지 (SUCCESS=정상처리, 실패처리건에 대한 상세메시지)", example = "OK")
	String description,

	@Schema(description = "데이터")
	T data

) {
}