package com.example.webtestproject.domain.kr_public.service;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.webtestproject.common.exception.XperpCustomException;
import com.example.webtestproject.common.util.DbUrlUtils;
import com.example.webtestproject.domain.kr_public.dto.PublicHolidayEnvelope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Slf4j
public class PublicServiceImpl implements PublicService {
	private final DbUrlUtils dbUrlUtils;

	@Override
	public PublicHolidayEnvelope.PublicHolidayBody getHoliday(int pageNo, int numOfRows, int solYear, String type) {

		RestClient restClient = RestClient.create();
		// 가상키 키는 공고
		String encodedKey = "null".trim();
		URI uri = UriComponentsBuilder.fromUriString(
				"https://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo")
			.queryParam("serviceKey", encodedKey)   // 이미 인코딩됨
			.queryParam("pageNo", pageNo)
			.queryParam("numOfRows", numOfRows)
			.queryParam("solYear", solYear)
			.queryParam("_type", type)              // "json"
			.build(true)                            // 재인코딩 금지 플래그
			.toUri();

		log.info("[PublicService] url: {}", uri);

		PublicHolidayEnvelope envelope;
		try {
			envelope = restClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(new ParameterizedTypeReference<PublicHolidayEnvelope>() {
				});

		} catch (Exception ex) {
			throw new XperpCustomException(ex);
		}

		if (envelope == null || envelope.getResponse() == null || envelope.getResponse().getHeader() == null) {
			throw new XperpCustomException("외부 API 무응답 또는 헤더 없음");
		}

		var header = envelope.getResponse().getHeader();
		if (!"00".equals(header.getResultCode())) {
			// 공공데이터 포털은 에러 시 의미있는 resultMsg를 내려줌
			throw new XperpCustomException(header.getResultMsg());
		}

		var body = envelope.getResponse().getBody();
		if (body == null) {
			throw new XperpCustomException("외부 API 응답 body 없음");
		}

		return body;
	}
}

