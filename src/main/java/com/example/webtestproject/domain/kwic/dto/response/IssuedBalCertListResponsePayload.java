package com.example.webtestproject.domain.kwic.dto.response;

import java.util.List;

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
@Schema(description = "잔액증명서 발급완료 내역 Payload")
class IssuedBalCertListResponsePayload {
	@Schema(description = "데이터 목록")
	private List<IssuedBalCertListData> dataList;
}