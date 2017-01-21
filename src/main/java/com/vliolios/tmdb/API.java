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
		return clazz.cast(clazz.equals(TVSearch.class) ? new TVSearch(apiKey) : new MovieSearch(apiKey));
    }
}
