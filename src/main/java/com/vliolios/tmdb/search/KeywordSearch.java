package com.vliolios.tmdb.search;

import java.io.IOException;

public class KeywordSearch extends Search {

	private KeywordSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<KeywordResult> submit() {
		try {
			return getSearchService().keyword(getApiKey(), getQuery(), getPage()).execute().body();
		} catch (IOException e) {
			Response<KeywordResult> invalidResponse = new Response<>();
			invalidResponse.setStatusCode(500);
			invalidResponse.setStatusMessage("Failed to parse the response body");
			invalidResponse.setSuccess(false);
			return invalidResponse;
		}
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, String baseUrl) {
		return new Builder(apiKey, baseUrl);
	}

	@Override
	public String getType() {
		return "keyword";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		KeywordSearch keywordSearch;

		private Builder(String apiKey, String baseUrl) {
			this.keywordSearch = new KeywordSearch(apiKey, baseUrl);
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

}
