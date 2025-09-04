package com.example.webtestproject.domain.kwic.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "잔액증명서 발급완료 내역조회 응답")
public class IssuedBalCertListResponse {
	@Schema(description = "결과코드", example = "0000")
	private String code;


	@Schema(description = "메시지", example = "정상")
	private String message;


	@Schema(description = "응답 페이로드")
	private IssuedBalCertListResponsePayload payload;


	public static IssuedBalCertListResponse error(String message, String code) {
		return IssuedBalCertListResponse.builder()
			.code(code)
			.message(message)
			.payload(null)
			.build();
	}
}
