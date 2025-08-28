package com.example.webtestproject.domain.kr_public.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.webtestproject.domain.kr_public.dto.HaptNewsItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HaptSitemapServiceImpl implements HaptSitemapService {

	private static final String SITEMAP_URL = "https://www.hapt.co.kr/sitemap.xml";
	private final RestClient restClient;

	public HaptSitemapServiceImpl() {
		// 필요 시 Bean으로 주입받아도 됨 (아래 Config 참고)
		this.restClient = RestClient.create();
	}

	@Override
	public List<HaptNewsItem> getSitemapItems() {
		try {
			URI uri = URI.create(SITEMAP_URL);
			log.info("[HAPT] GET {}", uri);

			byte[] body = restClient.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_XML)
				.retrieve()
				.body(byte[].class);

			if (body == null || body.length == 0) return List.of();
			try (InputStream is = new ByteArrayInputStream(body)) {
				return parse(is);
			}
		} catch (Exception e) {
			log.error("[HAPT] sitemap fetch/parse error", e);
			return List.of(); // 또는 커스텀 예외
		}
	}

	private List<HaptNewsItem> parse(InputStream is) throws Exception {
		List<HaptNewsItem> items = new ArrayList<>();

		XMLInputFactory f = XMLInputFactory.newFactory();
		f.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLStreamReader r = f.createXMLStreamReader(is, StandardCharsets.UTF_8.name());

		String url = null;
		OffsetDateTime lastmod = null;
		String title = null;
		OffsetDateTime pubDate = null;
		String keywords = null;
		String imageUrl = null;

		String currentElement = null;

		while (r.hasNext()) {
			int e = r.next();

			if (e == XMLStreamConstants.START_ELEMENT) {
				currentElement = r.getLocalName(); // news:title → "title"
				if ("url".equals(currentElement)) {
					url = null; lastmod = null; title = null; pubDate = null; keywords = null; imageUrl = null;
				}

			} else if (e == XMLStreamConstants.CHARACTERS || e == XMLStreamConstants.CDATA) {
				if (currentElement == null) continue;
				String text = r.getText().trim();
				if (text.isEmpty()) continue;

				switch (currentElement) {
					case "loc" -> {
						if (url == null) url = text;            // 첫 loc은 기사 URL
						else if (imageUrl == null && looksLikeImage(text)) imageUrl = text; // image:loc
					}
					case "lastmod" -> lastmod = parseTime(text);
					case "title" -> title = text;
					case "publication_date" -> pubDate = parseTime(text);
					case "keywords" -> keywords = text.replaceAll(",\\s*$", "");
				}

			} else if (e == XMLStreamConstants.END_ELEMENT) {
				String local = r.getLocalName();
				if ("url".equals(local)) {
					items.add(new HaptNewsItem(url, lastmod, title, pubDate, keywords, imageUrl));
				}
				currentElement = null;
			}
		}

		r.close();
		return items;
	}

	private static boolean looksLikeImage(String s) {
		String lower = s.toLowerCase();
		return lower.endsWith(".jpg") || lower.endsWith(".jpeg")
			|| lower.endsWith(".png") || lower.endsWith(".gif") || lower.endsWith(".webp");
	}

	private static OffsetDateTime parseTime(String s) {
		try { return OffsetDateTime.parse(s); } catch (Exception ignore) { return null; }
	}
}