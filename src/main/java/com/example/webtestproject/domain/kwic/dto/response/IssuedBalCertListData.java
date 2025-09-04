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
@Schema(description = "발급완료 단건")
class IssuedBalCertListData {
	@Schema(description = "잔액증명서 거래 아이디", example = "1f17a323-9a39-11ee-a511-eb694bc7872f")
	private String bcTrId;
	@Schema(description = "잔액확인일", example = "2024-01-01")
	private String targetDate;
	@Schema(description = "은행코드", example = "004")
	private String bankCode;
	@Schema(description = "은행명", example = "국민은행")
	private String bankName;
	@Schema(description = "발급일시", example = "2024-01-01 10:00:00")
	private String issueDate;
	@Schema(description = "계좌번호", example = "1234567890")
	private String accountNo;
}