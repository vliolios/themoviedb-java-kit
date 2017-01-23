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

	public <T extends Search<T, X>, X extends Result> T search(Class<T> clazz) {
		T search;
		if (clazz.equals(TVSearch.class)) {
			search = clazz.cast(new TVSearch(apiKey));
		} else if (clazz.equals(MovieSearch.class)) {
			search = clazz.cast(new MovieSearch(apiKey));
		} else if (clazz.equals(CollectionSearch.class)) {
			search = clazz.cast(new CollectionSearch(apiKey));
		} else if (clazz.equals(KeywordSearch.class)) {
			search = clazz.cast(new KeywordSearch(apiKey));
		} else {
			search = clazz.cast(new CompanySearch(apiKey));
		}
		return search;
    }
}
