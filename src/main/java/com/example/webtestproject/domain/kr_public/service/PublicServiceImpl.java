package com.example.webtestproject.domain.kr_public.service;

import org.springframework.stereotype.Service;

import com.example.webtestproject.common.util.DbUrlUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Slf4j
public class PublicServiceImpl implements PublicService {
	private final DbUrlUtils dbUrlUtils;
}


