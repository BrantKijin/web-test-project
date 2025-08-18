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
	@Schema(description = "요청 건수", example = "2")
	private Integer requestCnt;

	@JsonProperty("valid_cnt")
	@Schema(description = "유효(일치) 사업자 수", example = "1")
	private Integer validCnt;

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
		@Schema(description = "사업자등록번호", example = "1208614122")
		private String bNo;

		@Schema(description = "진위 결과 코드(01: 일치, 02: 확인불가 등)", example = "01")
		private String valid;

		@JsonProperty("valid_msg")
		@Schema(description = "진위 결과 메시지(확인불가 등일 때 존재)", example = "확인할 수 없습니다.")
		private String validMsg;

		@JsonProperty("request_param")
		@Schema(description = "요청에 사용된 원본 파라미터")
		private RequestParam requestParam;

		@JsonProperty("status")
		@Schema(description = "정상 사업자일 때 내려오는 상세 상태 정보")
		private Status status;

		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class RequestParam {
			@JsonProperty("b_no")     @Schema(example = "1208614122") private String bNo;
			@JsonProperty("start_dt") @Schema(example = "20001113")    private String startDt;
			@JsonProperty("p_nm")     @Schema(example = "최병인")       private String pNm;
			@JsonProperty("p_nm2")    @Schema(example = "")            private String pNm2;
			@JsonProperty("b_nm")     @Schema(example = "")            private String bNm;
			@JsonProperty("corp_no")  @Schema(example = "")            private String corpNo;
			@JsonProperty("b_type")   @Schema(example = "")            private String bType;
			@JsonProperty("b_sector") @Schema(example = "")            private String bSector;
			@JsonProperty("b_adr")    @Schema(example = "")            private String bAdr;
		}

		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Status {
			@JsonProperty("b_no")                 @Schema(example = "1208614122")            private String bNo;
			@JsonProperty("b_stt")                @Schema(description = "사업자 상태", example = "계속사업자") private String bStt;
			@JsonProperty("b_stt_cd")             @Schema(description = "사업자 상태 코드", example = "01")   private String bSttCd;
			@JsonProperty("tax_type")             @Schema(description = "과세유형", example = "부가가치세 일반과세자") private String taxType;
			@JsonProperty("tax_type_cd")          @Schema(description = "과세유형 코드", example = "01")   private String taxTypeCd;
			@JsonProperty("end_dt")               @Schema(description = "폐업일자", example = "")         private String endDt;
			@JsonProperty("utcc_yn")              @Schema(description = "국세청 휴폐업 조회 연동 여부", example = "N") private String utccYn;
			@JsonProperty("tax_type_change_dt")   @Schema(description = "과세유형 변경일자", example = "")  private String taxTypeChangeDt;
			@JsonProperty("invoice_apply_dt")     @Schema(description = "전자(세금)계산서 적용일자", example = "") private String invoiceApplyDt;
			@JsonProperty("rbf_tax_type")         @Schema(description = "간이과세 배제 적용 여부(문구)", example = "해당없음") private String rbfTaxType;
			@JsonProperty("rbf_tax_type_cd")      @Schema(description = "간이과세 배제 적용 코드", example = "99")         private String rbfTaxTypeCd;
		}
	}
}