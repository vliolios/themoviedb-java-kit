package com.vliolios.tmdb.search;

public class KeywordSearch extends Search {

	private KeywordSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<KeywordResult> submit() {
		return submit(searchService -> searchService.keyword(getApiKey(), getQuery(), getPage()));
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
