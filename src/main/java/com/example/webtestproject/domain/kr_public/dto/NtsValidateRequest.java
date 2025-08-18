package com.example.webtestproject.domain.kr_public.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "NtsValidateRequest", description = "사업자등록정보 진위확인 요청 바디")
public class NtsValidateRequest {

	@NotNull
	@Size(min = 1, message = "businesses는 최소 1건 이상이어야 합니다.")
	@Schema(description = "조회 대상 사업자 목록", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<Business> businesses;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Schema(name = "NtsBusiness", description = "사업자 단건 검증 요청")
	public static class Business {
		@NotBlank
		@JsonProperty("b_no")
		@Schema(description = "사업자등록번호", example = "0000000000", requiredMode = Schema.RequiredMode.REQUIRED)
		private String bNo;

		@NotBlank
		@JsonProperty("start_dt")
		@Schema(description = "개업일자(YYYYMMDD)", example = "20000101", requiredMode = Schema.RequiredMode.REQUIRED)
		private String startDt;

		@NotBlank
		@JsonProperty("p_nm")
		@Schema(description = "대표자성명1", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
		private String pNm;

		@JsonProperty("p_nm2")
		@Schema(description = "대표자성명2(대표자성명1이 한글이 아닌 경우 한글명)", example = "홍길동")
		private String pNm2;

		@JsonProperty("b_nm")
		@Schema(description = "상호(선택)", example = "(주)테스트")
		private String bNm;

		@JsonProperty("corp_no")
		@Schema(description = "법인등록번호(선택)", example = "0000000000000")
		private String corpNo;

		@JsonProperty("b_sector")
		@Schema(description = "주업태명(선택)", example = "")
		private String bSector;

		@JsonProperty("b_type")
		@Schema(description = "주종목명(선택)", example = "")
		private String bType;

		@JsonProperty("b_adr")
		@Schema(description = "사업장주소(선택)", example = "")
		private String bAdr;
	}
}