package com.example.webtestproject.common.dto.external.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record JpaToIdcDataPageInfo<T>(

	@Schema(description = "데이터 리스트")
	List<T> dtoList,

	@Schema(description = "전체 페이지 수")
	Integer totalPage,

	@Schema(description = "현재 페이지")
	Integer page,

	@Schema(description = "페이지 크기")
	Integer size,

	@Schema(description = "불러온 첫 페이지")
	Integer start,

	@Schema(description = "불러온 마지막 페이지")
	Integer end,

	@Schema(description = "이전페이지 존재여부")
	Boolean prev,

	@Schema(description = "다음페이지 존재여부")
	Boolean next,

	// @Schema(description = "현재 사용 페이지 번호 전부")
	// Integer[] pageList,

	@Schema(description = "총 데이터 수")
	Integer totalElement

) {}