package com.example.webtestproject.domain.kwic.service;

import com.example.webtestproject.domain.kwic.dto.request.IssuedBalCertListRequest;
import com.example.webtestproject.domain.kwic.dto.response.IssuedBalCertListResponse;

public interface AptBankExternalService {
	IssuedBalCertListResponse getIssuedBalCertList(IssuedBalCertListRequest request);
}
