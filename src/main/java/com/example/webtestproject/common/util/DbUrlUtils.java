package com.example.webtestproject.common.util;


import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.webtestproject.common.dto.external.response.JpaToIdcDataPageResponse;
import com.example.webtestproject.common.dto.external.response.JpaToIdcDataResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class DbUrlUtils {
	private static final int PAGE_SIZE = 5000;
	private final RestClient.Builder restClientBuilder;

	private final Environment env;

	/**
	 * 페이징 데이터 조회 (페이징 관련 정보가 추가됨)
	 * @param url
	 * @param subUrl
	 * @param parameter
	 * @param responseType
	 * @return
	 * @param <T>
	 */
	public <T> JpaToIdcDataPageResponse<T> getPageResponse(String url, String subUrl, String parameter,
		ParameterizedTypeReference<JpaToIdcDataPageResponse<T>> responseType) {
		RestClient restClient = restClientBuilder.build();
		return restClient.get()
			.uri(url + subUrl + parameter)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(responseType);
	}

	/**
	 * 단건 조회 (페이징 정보가 X)
	 * @param url
	 * @param subUrl
	 * @param parameter
	 * @param responseType
	 * @return
	 * @param <T>
	 */
	public <T> JpaToIdcDataResponse<T> getResponse(String url, String subUrl, String parameter,
		ParameterizedTypeReference<JpaToIdcDataResponse<T>> responseType) {
		RestClient restClient = restClientBuilder.build();
		return restClient.get()
			.uri(url + subUrl + parameter)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(responseType);
	}

	//전체 DB 를 불러와서 확인하는 로직 제네릭형식으로 변경 미사용 이유 복잡도
	public <T, R> void getAllDataWithPaging(String url, String subUrl,
		ParameterizedTypeReference<JpaToIdcDataPageResponse<R>> responseType,
		List<T> resultList, Function<R, T> mapper) {
		int page = 1;
		boolean hasData = true;
		while (hasData) {
			log.info("getAllDataWithPaging:url:{}, page:{}, size:{}", url, page, PAGE_SIZE);
			JpaToIdcDataPageResponse<R> response = getPageResponse(url, subUrl, "?page=" + page + "&size=" + PAGE_SIZE,
				responseType);
			if (response != null && response.data() != null) {
				List<R> result = response.data().dtoList();
				List<T> mappedList = result.stream().map(mapper).toList();
				resultList.addAll(mappedList);
				hasData = !result.isEmpty();
				page++;
			} else {
				hasData = false;
			}
		}
	}

	/**
	 * 외부 api 다건 조회 (페이징 정보가 X)
	 * @param url
	 * @param subUrl
	 * @param parameter
	 * @param responseType
	 * @param keyHeader
	 * @return
	 * @param List<T>
	 */
	public <T> List<T> getResponseList(
		String url, String subUrl, String parameter,
		ParameterizedTypeReference<List<T>> responseType,
		Map<String, String> keyHeader
	) {
		RestClient restClient = restClientBuilder.build();
		return restClient.get()
			.uri(url + subUrl + parameter)
			.header(keyHeader.get("KEY_NAME"), keyHeader.get("KEY_VALUE"))
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(responseType);
	}

	/**
	 * 외부 api 단건 조회 (페이징 정보가 X)
	 * @param url
	 * @param subUrl
	 * @param parameter
	 * @param responseType
	 * @param keyHeader
	 * @return
	 * @param T
	 */
	public <T> T getResponseOne(
		String url, String subUrl, String parameter,
		ParameterizedTypeReference<T> responseType,
		Map<String, String> keyHeader
	) {
		RestClient restClient = restClientBuilder.build();
		return restClient.get()
			.uri(url + subUrl + parameter)
			.header(keyHeader.get("KEY_NAME"), keyHeader.get("KEY_VALUE"))
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(responseType);
	}

	/**
	 * 외부 api 단건 조회 (페이징 정보가 X)
	 * @param url
	 * @param subUrl
	 * @param parameter
	 * @param responseType
	 * @return
	 */
	public <T> T getResponseSJOne(
		String url, String subUrl, String parameter,
		ParameterizedTypeReference<T> responseType,
		Map<String, String> keyHeader
	) {
		RestClient restClient = restClientBuilder.build();
		return restClient.get()
			.uri(url + subUrl + parameter)
			.header(keyHeader.get("API-KEY"))
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(responseType);
	}

}
