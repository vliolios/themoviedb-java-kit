package com.vliolios.tmdb.search;

import org.springframework.web.client.RestTemplate;

public class CollectionSearch extends Search<CollectionResult> {

	private String language;
	
	private CollectionSearch(String apiKey, RestTemplate restTemplate) {
		super(apiKey, restTemplate);
	}

	public static SearchWithQuery apiKey(String apiKey, RestTemplate restTemplate) {
		return new Builder(apiKey, restTemplate);
	}

	public String getLanguage() {
		return language;
	}
	
	@Override
	protected String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		return sb.toString();
	}

	@Override
	public String getType() {
		return "collection";
	}

	@Override
	public Class<CollectionResult> getResponseType() {
		return CollectionResult.class;
	}

	public static class Builder implements SearchWithQuery {
		CollectionSearch collectionSearch;

		private Builder(String apiKey, RestTemplate restTemplate) {
			this.collectionSearch = new CollectionSearch(apiKey, restTemplate);
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

	public interface SearchWithQuery {
		Builder query(String query);
	}
}
