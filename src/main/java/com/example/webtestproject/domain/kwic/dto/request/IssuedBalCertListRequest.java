package com.example.webtestproject.domain.kwic.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "잔액증명서 발급완료 내역조회 요청")
public class IssuedBalCertListRequest {
	@Schema(description = "단지 코드", example = "00001")
	@NotBlank
	private String complexCode;


	@Schema(description = "은행 코드", example = "004")
	@NotBlank
	private String bankCode;


	@Schema(description = "조회 시작일(yyyy-MM-dd)", example = "2024-01-01")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private String startDt;


	@Schema(description = "조회 종료일(yyyy-MM-dd)", example = "2024-12-01")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private String endDt;


	@Schema(description = "사용자 ID", example = "userId")
	@NotBlank private String userId;
}