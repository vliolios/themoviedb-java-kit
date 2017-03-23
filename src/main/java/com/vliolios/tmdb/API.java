package com.vliolios.tmdb;

import com.vliolios.tmdb.search.*;

public class API {

	private static final String DEFAULT_BASE_URL = "https://api.themoviedb.org/3/";

	private APIConfig apiConfig;

	public API(String apiKey) {
		this(apiKey, DEFAULT_BASE_URL);
	}

	API(String apiKey, String baseUrl) {
		this.apiConfig = new APIConfig(apiKey, baseUrl);
	}

	public SearchSelector search() {
		return new SearchSelector();
    }
	
	public class SearchSelector {

		private SearchSelector() {}

		public SearchWithQuery<TVSearch.Builder> tv() {
			return TVSearch.apiConfig(apiConfig);
		}
		
		public SearchWithQuery<MovieSearch.Builder> movie() {
			return MovieSearch.apiConfig(apiConfig);
		}
		
		public SearchWithQuery<CollectionSearch.Builder> collection() {
			return CollectionSearch.apiConfig(apiConfig);
		}
		
		public SearchWithQuery<KeywordSearch.Builder> keyword() {
			return KeywordSearch.apiConfig(apiConfig);
		}
		
		public SearchWithQuery<PeopleSearch.Builder> people() {
			return PeopleSearch.apiConfig(apiConfig);
		}
		
		public SearchWithQuery<MultiSearch.Builder> multi() {
			return MultiSearch.apiConfig(apiConfig);
		}
		
		public SearchWithQuery<CompanySearch.Builder> company() {
			return CompanySearch.apiConfig(apiConfig);
		}
		
	}
}
