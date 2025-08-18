package com.example.webtestproject.common.dto.external.response;

import io.swagger.v3.oas.annotations.media.Schema;

//아파트아이 커뮤니티 게시판쪽 공통 Response
public record AptICommunityDataResponse<T>(

	@Schema(description = "응답코드 (0 = 정상처리0외의 값 실패)", example = "0")
	String returnCode,

	@Schema(description = "응답메시지 (OK=정상처리, 실패처리건에 대한 상세메시지)", example = "OK")
	String returnMessage,

	@Schema(description = "데이터")
	T data

) {
}