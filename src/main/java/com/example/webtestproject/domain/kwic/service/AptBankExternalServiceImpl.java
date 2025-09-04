package com.example.webtestproject.domain.kwic.service;

import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.webtestproject.common.exception.XperpCustomException;
import com.example.webtestproject.common.util.DbUrlUtils;
import com.example.webtestproject.domain.kwic.dto.request.IssuedBalCertListRequest;
import com.example.webtestproject.domain.kwic.dto.response.IssuedBalCertListResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AptBankExternalServiceImpl implements AptBankExternalService {

	private final DbUrlUtils dbUrlUtils;

	@Override
	public IssuedBalCertListResponse getIssuedBalCertList(IssuedBalCertListRequest request) {
		RestClient restClient = RestClient.create();
		try {
			IssuedBalCertListResponse body = restClient.post()

				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(request)
				.retrieve()
				.body(IssuedBalCertListResponse.class);

			System.out.println();
			if (!"0000".equals(Objects.requireNonNull(body).getCode())) {
				throw new XperpCustomException(400, body.getMessage());
			}
			return body;
		} catch (Exception e) {
			log.error("getIssuedBalCertList API 오류", e);
			throw new  XperpCustomException("getIssuedBalCertList API:"+e);
		}
	}
}
