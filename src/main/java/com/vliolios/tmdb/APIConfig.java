package com.vliolios.tmdb;

/**
 * Created by vasileiosl on 23/03/2017.
 */
public class APIConfig {

	private String apiKey;
	private String baseUrl;

	public APIConfig(String apiKey, String baseUrl) {
		this.apiKey = apiKey;
		this.baseUrl = baseUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getBaseUrl() {
		return baseUrl;
	}
}
