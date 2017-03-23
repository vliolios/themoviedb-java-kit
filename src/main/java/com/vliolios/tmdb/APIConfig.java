package com.vliolios.tmdb;

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
