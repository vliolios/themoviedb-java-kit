package com.vliolios.tmdb;

import com.vliolios.tmdb.search.*;
import org.springframework.web.client.RestTemplate;

public class API {

	public static SearchSelector search(String apiKey) {
		return new SearchSelector(apiKey);
    }
	
	public static class SearchSelector {

    	private final String apiKey;

		public SearchSelector(String apiKey) {
			this.apiKey = apiKey;
		}

		public SearchWithQuery<TVSearch.Builder> tv() {
			return TVSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public SearchWithQuery<MovieSearch.Builder> movie() {
			return MovieSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public SearchWithQuery<CollectionSearch.Builder> collection() {
			return CollectionSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public SearchWithQuery<KeywordSearch.Builder> keyword() {
			return KeywordSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public SearchWithQuery<PeopleSearch.Builder> people() {
			return PeopleSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public SearchWithQuery<MultiSearch.Builder> multi() {
			return MultiSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public SearchWithQuery<CompanySearch.Builder> company() {
			return CompanySearch.apiKey(apiKey, new RestTemplate());
		}
		
	}
}
