/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vliolios.tmdb;

/**
 *
 * @author vliolios
 */
public class API {

    private final String apiKey;

    public API(String apiKey) {
    	this.apiKey = apiKey;
    }

	public SearchSelector search() {
		return new SearchSelector();
    }
	
	public class SearchSelector {
		
		public TVSearch tv() {
			return new TVSearch(apiKey);
		}
		
		public MovieSearch movie() {
			return new MovieSearch(apiKey);
		}
		
		public CollectionSearch collection() {
			return new CollectionSearch(apiKey);
		}
		
		public KeywordSearch keyword() {
			return new KeywordSearch(apiKey);
		}
		public PeopleSearch people() {
			return new PeopleSearch(apiKey);
		}
		public MultiSearch multi() {
			return new MultiSearch(apiKey);
		}
		public CompanySearch company() {
			return new CompanySearch(apiKey);
		}
		
	}
}
