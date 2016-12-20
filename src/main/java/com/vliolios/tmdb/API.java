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

    public Search search() {
    	return new Search(apiKey);
    }
}
