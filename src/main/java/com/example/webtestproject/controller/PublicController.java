package com.example.webtestproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webtestproject.common.dto.ApiResponse;
import com.example.webtestproject.domain.kr_public.dto.HaptNewsItem;
import com.example.webtestproject.domain.kr_public.dto.NtsValidateRequest;
import com.example.webtestproject.domain.kr_public.dto.NtsValidateResponse;
import com.example.webtestproject.domain.kr_public.dto.PublicHolidayEnvelope;
import com.example.webtestproject.domain.kr_public.service.HaptSitemapService;
import com.example.webtestproject.domain.kr_public.service.PublicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;



@Tag(name = "[XP비서] 공공데이터 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/public")
public class PublicController {

	private final PublicService publicService;

	@Operation(summary = "[XP비서] 공휴일조회", tags = {"SOJANG"})
	@GetMapping("/holiday")
	public ApiResponse<PublicHolidayEnvelope.PublicHolidayBody> getNotificationsList(
		@Parameter(description = "페이지 번호", example = "1")
		@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
		@Parameter(description = "페이지당 조회건수", example = "100")
		@RequestParam(value = "numOfRows", required = false, defaultValue = "100") int numOfRows,
		@Parameter(description = "조회년도", example = "2025")
		@RequestParam(value = "solYear", required = false, defaultValue = "2025") int solYear,
		@Parameter(description = "반환타입", example = "json")
		@RequestParam(value = "type", required = false, defaultValue = "json") String type
	) {

		PublicHolidayEnvelope.PublicHolidayBody holiday = publicService.getHoliday(pageNo, numOfRows, solYear, type);
		return ApiResponse.success(holiday);
	}

	@Operation(summary = "[XP비서] 사업자번호 진위확인", tags = {"SOJANG"})
	@PostMapping("/nts/validate")
	public ApiResponse<NtsValidateResponse> validate(
		@RequestBody @Valid NtsValidateRequest request
	) {
		NtsValidateResponse result = publicService.validateBusinesses(request);
		return ApiResponse.success(result);
	}

	private final HaptSitemapService service;

	@Operation(summary = "[XP비서] 한국아파트신문 sitemap 프록시(JSON)", tags = {"SOJANG"})
	@GetMapping("/sitemap")
	public ApiResponse<List<HaptNewsItem>> getSitemap(
		@Parameter(description = "최대 반환 건수(최신부터)", example = "100")
		@RequestParam(value = "limit", required = false, defaultValue = "200") int limit
	) {
		List<HaptNewsItem> all = service.getSitemapItems();
		// 최신순 보장은 원문이 최신순이라 가정. 혹시 몰라 앞부분만 슬라이스.
		List<HaptNewsItem> sliced = all.size() > limit ? all.subList(0, limit) : all;
		return ApiResponse.success(sliced);
	}
}
