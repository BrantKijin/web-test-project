package com.example.webtestproject.domain.kr_public.dto;

// package ...dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "PublicHolidayEnvelope", description = "공공데이터 공휴일 조회 최상위 응답")
public class PublicHolidayEnvelope {

	@Schema(description = "응답 본문")
	private PublicHolidayResponse response;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Schema(name = "PublicHolidayResponse", description = "응답 헤더/바디")
	public static class PublicHolidayResponse {

		@Schema(description = "헤더", requiredMode = Schema.RequiredMode.REQUIRED)
		private PublicHolidayHeader header;

		@Schema(description = "바디", requiredMode = Schema.RequiredMode.REQUIRED)
		private PublicHolidayBody body;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Schema(name = "PublicHolidayHeader", description = "헤더(결과코드/메시지)")
	public static class PublicHolidayHeader {

		@Schema(description = "결과코드", example = "00", maxLength = 2, requiredMode = Schema.RequiredMode.REQUIRED)
		private String resultCode;

		@Schema(description = "결과메시지", example = "OK", maxLength = 50, requiredMode = Schema.RequiredMode.REQUIRED)
		private String resultMsg;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Schema(name = "PublicHolidayBody", description = "바디(목록/페이징)")
	public static class PublicHolidayBody {

		@Schema(description = "목록 래퍼", requiredMode = Schema.RequiredMode.REQUIRED)
		private Items items;

		@Schema(description = "한 페이지 결과 수", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
		private Integer numOfRows;

		@Schema(description = "페이지 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		private Integer pageNo;

		@Schema(description = "전체 결과 수", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
		private Integer totalCount;

		@Data
		@JsonIgnoreProperties(ignoreUnknown = true)
		@Schema(name = "PublicHolidayItems", description = "목록 컨테이너")
		public static class Items {

			@Schema(description = "공휴일/기념일 항목 리스트", requiredMode = Schema.RequiredMode.REQUIRED)
			private List<Item> item;
		}

		@Data
		@JsonIgnoreProperties(ignoreUnknown = true)
		@Schema(name = "PublicHolidayItem", description = "공휴일/기념일 단건")
		public static class Item {

			@Schema(description = "날짜(YYYYMMDD)", example = "20190228", maxLength = 8, requiredMode = Schema.RequiredMode.REQUIRED)
			private Integer locdate;

			@Schema(description = "순번", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
			private Integer seq;

			@Schema(description = "종류(특일정보 분류: 02=기념일 등)", example = "02", maxLength = 2, requiredMode = Schema.RequiredMode.REQUIRED)
			private String dateKind;

			@Schema(description = "공공기관 휴일 여부", example = "N", maxLength = 1, requiredMode = Schema.RequiredMode.REQUIRED)
			private String isHoliday;

			@Schema(description = "명칭", example = "2·28 민주운동 기념일", maxLength = 50, requiredMode = Schema.RequiredMode.REQUIRED)
			private String dateName;
		}
	}
}
