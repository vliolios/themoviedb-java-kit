package com.vliolios.tmdb;

import com.vliolios.tmdb.search.*;

public class API {

	private static final String DEFAULT_BASE_URL = "https://api.themoviedb.org/3/";

	private String apiKey;
	private String baseUrl;

	public API(String apiKey) {
		this(apiKey, DEFAULT_BASE_URL);
	}

	API(String apiKey, String baseUrl) {
		this.apiKey = apiKey;
		this.baseUrl = baseUrl;
	}

	public SearchSelector search() {
		return new SearchSelector();
    }
	
	public class SearchSelector {

		private SearchSelector() {}

		public SearchWithQuery<TVSearch.Builder> tv() {
			return TVSearch.apiKey(apiKey,  baseUrl);
		}
		
		public SearchWithQuery<MovieSearch.Builder> movie() {
			return MovieSearch.apiKey(apiKey, baseUrl);
		}
		
		public SearchWithQuery<CollectionSearch.Builder> collection() {
			return CollectionSearch.apiKey(apiKey, baseUrl);
		}
		
		public SearchWithQuery<KeywordSearch.Builder> keyword() {
			return KeywordSearch.apiKey(apiKey, baseUrl);
		}
		
		public SearchWithQuery<PeopleSearch.Builder> people() {
			return PeopleSearch.apiKey(apiKey, baseUrl);
		}
		
		public SearchWithQuery<MultiSearch.Builder> multi() {
			return MultiSearch.apiKey(apiKey, baseUrl);
		}
		
		public SearchWithQuery<CompanySearch.Builder> company() {
			return CompanySearch.apiKey(apiKey, baseUrl);
		}
		
	}
}
