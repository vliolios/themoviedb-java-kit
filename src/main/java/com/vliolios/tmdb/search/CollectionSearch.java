package com.vliolios.tmdb.search;

import com.vliolios.tmdb.APIConfig;

public class CollectionSearch extends Search {

	private String language;
	
	private CollectionSearch(APIConfig apiConfig) {
		super(apiConfig);
	}

	public Response<CollectionResult> submit() {
		return submit(searchService -> searchService.collection(getApiKey(), getQuery(), getPage(), language));
	}

	public static SearchWithQuery<Builder> apiConfig(APIConfig apiConfig) {
		return new Builder(apiConfig);
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

		private Builder(APIConfig apiConfig) {
			this.collectionSearch = new CollectionSearch(apiConfig);
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
