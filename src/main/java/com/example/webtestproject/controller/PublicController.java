package com.example.webtestproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webtestproject.common.dto.ApiResponse;
import com.example.webtestproject.domain.kr_public.dto.PublicHolidayEnvelope;
import com.example.webtestproject.domain.kr_public.service.PublicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
}
