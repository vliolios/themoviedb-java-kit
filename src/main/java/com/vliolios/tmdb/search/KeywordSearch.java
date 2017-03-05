package com.vliolios.tmdb.search;

import org.springframework.web.client.RestTemplate;

public class KeywordSearch extends Search<KeywordResult> {

	private KeywordSearch(String apiKey, RestTemplate restTemplate) {
		super(apiKey, restTemplate);
	}

	public static SearchWithQuery apiKey(String apiKey, RestTemplate restTemplate) {
		return new Builder(apiKey, restTemplate);
	}

	@Override
	public String getType() {
		return "keyword";
	}

	@Override
	public Class<KeywordResult> getResponseType() {
		return KeywordResult.class;
	}

	public static class Builder implements KeywordSearch.SearchWithQuery {
		KeywordSearch keywordSearch;

		private Builder(String apiKey, RestTemplate restTemplate) {
			this.keywordSearch = new KeywordSearch(apiKey, restTemplate);
		}

		public Builder query(String query) {
			keywordSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			keywordSearch.page(page);
			return this;
		}

		public KeywordSearch build() {
			return keywordSearch;
		}

	}

	public interface SearchWithQuery {
		KeywordSearch.Builder query(String query);
	}

}
