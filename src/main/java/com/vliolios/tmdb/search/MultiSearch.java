package com.vliolios.tmdb.search;

import java.io.IOException;

public class MultiSearch extends Search {
	
	private String language;
	private Boolean includeAdult;
	private String region;

	private MultiSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<MultiResult> submit() {
		try {
			return getSearchService().multi(getApiKey(), getQuery(), getPage(), language, includeAdult, region).execute().body();
		} catch (IOException e) {
			Response<MultiResult> invalidResponse = new Response<>();
			invalidResponse.setStatusCode(500);
			invalidResponse.setStatusMessage("Failed to parse the response body");
			invalidResponse.setSuccess(false);
			return invalidResponse;
		}
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, String baseUrl) {
		return new Builder(apiKey, baseUrl);
	}
	
	public String getLanguage() {
		return language;
	}
	
	public Boolean getIncludeAdult() {
		return includeAdult;
	}

	public String getRegion() {
		return region;
	}

	@Override
	public String getType() {
		return "multi";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		MultiSearch multiSearch;

		private Builder(String apiKey, String baseUrl) {
			this.multiSearch = new MultiSearch(apiKey, baseUrl);
		}

		public Builder query(String query) {
			multiSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			multiSearch.page(page);
			return this;
		}

		public Builder language(String language) {
			multiSearch.language = language;
			return this;
		}

		public Builder includeAdult(Boolean includeAdult) {
			multiSearch.includeAdult = includeAdult;
			return this;
		}

		public Builder region(String region) {
			multiSearch.region = region;
			return this;
		}

		public MultiSearch build() {
			return multiSearch;
		}
	}
}
