package com.example.webtestproject.domain.kr_public.service;

import com.example.webtestproject.domain.kr_public.dto.NtsValidateRequest;
import com.example.webtestproject.domain.kr_public.dto.NtsValidateResponse;
import com.example.webtestproject.domain.kr_public.dto.PublicHolidayEnvelope;

public interface PublicService {
	PublicHolidayEnvelope.PublicHolidayBody getHoliday(int pageNo, int numOfRows, int solYear, String type);
	NtsValidateResponse validateBusinesses(NtsValidateRequest request);
}
