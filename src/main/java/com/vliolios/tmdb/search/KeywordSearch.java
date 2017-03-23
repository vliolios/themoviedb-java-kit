package com.vliolios.tmdb.search;

import com.vliolios.tmdb.APIConfig;

public class KeywordSearch extends Search {

	private KeywordSearch(APIConfig apiConfig) {
		super(apiConfig);
	}

	public Response<KeywordResult> submit() {
		return submit(searchService -> searchService.keyword(getApiKey(), getQuery(), getPage()));
	}

	public static SearchWithQuery<Builder> apiConfig(APIConfig apiConfig) {
		return new Builder(apiConfig);
	}

	@Override
	public String getType() {
		return "keyword";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		KeywordSearch keywordSearch;

		private Builder(APIConfig apiConfig) {
			this.keywordSearch = new KeywordSearch(apiConfig);
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
