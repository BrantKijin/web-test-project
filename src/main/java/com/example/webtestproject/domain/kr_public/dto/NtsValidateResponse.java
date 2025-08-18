package com.example.webtestproject.domain.kr_public.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "NtsValidateResponse", description = "사업자등록정보 진위확인 응답 바디")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NtsValidateResponse {

	@JsonProperty("request_cnt")
	@Schema(description = "요청 건수", example = "1")
	private Integer requestCnt;

	@JsonProperty("status_code")
	@Schema(description = "상태 코드", example = "OK")
	private String statusCode;

	@Schema(description = "검증 결과 목록")
	private List<Item> data;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Item {
		@JsonProperty("b_no")
		@Schema(description = "사업자등록번호", example = "0000000000")
		private String bNo;

		@Schema(description = "진위 결과 코드(01: 일치, 02: 확인불가 등)", example = "02")
		private String valid;

		@JsonProperty("valid_msg")
		@Schema(description = "진위 결과 메시지", example = "확인할 수 없습니다.")
		private String validMsg;

		@JsonProperty("request_param")
		@Schema(description = "요청에 사용된 원본 파라미터")
		private RequestParam requestParam;

		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class RequestParam {
			@JsonProperty("b_no")     @Schema(example = "0000000000") private String bNo;
			@JsonProperty("start_dt") @Schema(example = "20000101")    private String startDt;
			@JsonProperty("p_nm")     @Schema(example = "홍길동")         private String pNm;
			@JsonProperty("p_nm2")    @Schema(example = "홍길동")         private String pNm2;
			@JsonProperty("b_nm")     @Schema(example = "(주)테스트")       private String bNm;
			@JsonProperty("corp_no")  @Schema(example = "0000000000000")private String corpNo;
			@JsonProperty("b_type")   @Schema(example = "")             private String bType;
			@JsonProperty("b_sector") @Schema(example = "")             private String bSector;
			@JsonProperty("b_adr")    @Schema(example = "")             private String bAdr;
		}
	}
}