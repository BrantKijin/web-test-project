package com.example.webtestproject.controller.kwic;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webtestproject.common.dto.ApiResponse;
import com.example.webtestproject.domain.kwic.dto.request.IssuedBalCertListRequest;
import com.example.webtestproject.domain.kwic.dto.response.IssuedBalCertListResponse;
import com.example.webtestproject.domain.kwic.service.AptBankExternalService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "[기웅] API 외부호출 확인용")
@RestController
@RequiredArgsConstructor
@RequestMapping("/kwic/v1/external")
@Slf4j
public class KwicExternalController {
	private final AptBankExternalService aptBankExternalService;

	@Operation(summary = "잔액증명서 발급완료 내역조회")
	@PostMapping(value = "/bal-cert/issued-list")
	public ApiResponse<IssuedBalCertListResponse> getIssuedBalCertList(
		@Valid @RequestBody IssuedBalCertListRequest request
	) {
		IssuedBalCertListResponse external = aptBankExternalService.getIssuedBalCertList(request);
		return ApiResponse.success(external);
	}
}