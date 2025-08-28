package com.example.webtestproject.domain.kr_public.dto;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "HaptNewsItem", description = "한국아파트신문 sitemap 항목")
public record HaptNewsItem(
	@Schema(description = "기사 URL", example = "https://www.hapt.co.kr/news/articleView.html?idxno=166205")
	String url,

	@Schema(description = "마지막 수정일 (sitemap lastmod)", example = "2025-08-28T09:22:00+09:00")
	OffsetDateTime lastModified,

	@Schema(description = "뉴스 제목")
	String title,

	@Schema(description = "뉴스 게시일 (news:publication_date)", example = "2025-08-28T09:22:00+09:00")
	OffsetDateTime publicationDate,

	@Schema(description = "키워드 CSV", example = "한국아파트신문, 캠핑카, 화재")
	String keywords,

	@Schema(description = "대표 이미지 URL", example = "https://cdn.hapt.co.kr/news/photo/202508/166205_38040_723.png")
	String imageUrl
) {}