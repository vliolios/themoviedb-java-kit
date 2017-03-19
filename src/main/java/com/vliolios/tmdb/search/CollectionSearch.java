package com.vliolios.tmdb.search;

import java.io.IOException;

public class CollectionSearch extends Search {

	private String language;
	
	private CollectionSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<CollectionResult> submit() {
		try {
			return getSearchService().collection(getApiKey(), getQuery(), getPage(), language).execute().body();
		} catch (IOException e) {
			Response<CollectionResult> invalidResponse = new Response<>();
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

	@Override
	public String getType() {
		return "collection";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		CollectionSearch collectionSearch;

		private Builder(String apiKey, String baseUrl) {
			this.collectionSearch = new CollectionSearch(apiKey, baseUrl);
		}

		public Builder query(String query) {
			collectionSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			collectionSearch.page(page);
			return this;
		}

		public Builder language(String language) {
			collectionSearch.language = language;
			return this;
		}

		public CollectionSearch build() {
			return collectionSearch;
		}

	}
}
