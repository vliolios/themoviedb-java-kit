package com.vliolios.tmdb;

import com.vliolios.tmdb.search.CollectionSearch;
import com.vliolios.tmdb.search.CompanySearch;
import com.vliolios.tmdb.search.KeywordSearch;
import com.vliolios.tmdb.search.MovieSearch;
import com.vliolios.tmdb.search.MultiSearch;
import com.vliolios.tmdb.search.PeopleSearch;
import com.vliolios.tmdb.search.TVSearch;
import org.springframework.web.client.RestTemplate;

public class API {

    private final String apiKey;

    public API(String apiKey) {
    	this.apiKey = apiKey;
    }

	public SearchSelector search() {
		return new SearchSelector();
    }
	
	public class SearchSelector {
		
		public TVSearch.SearchWithQuery tv() {
			return TVSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public MovieSearch.SearchWithQuery movie() {
			return MovieSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public CollectionSearch.SearchWithQuery collection() {
			return CollectionSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public KeywordSearch.SearchWithQuery keyword() {
			return KeywordSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public PeopleSearch.SearchWithQuery people() {
			return PeopleSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public MultiSearch.SearchWithQuery multi() {
			return MultiSearch.apiKey(apiKey, new RestTemplate());
		}
		
		public CompanySearch.SearchWithQuery company() {
			return CompanySearch.apiKey(apiKey, new RestTemplate());
		}
		
	}
}
