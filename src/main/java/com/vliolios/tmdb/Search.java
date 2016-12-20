/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vliolios.tmdb;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 *
 * @author vliolios
 */
public class Search { 
	
	private static final String DOMAIN = "https://api.themoviedb.org/3";

    public enum Type {
		TV("tv");
	
		private final String text;
	
		Type(String text) {
		    this.text = text;
		}
	
		public String getText() {
		    return this.text;
		}
    }

    private String apiKey;
    private String type;
    private String query;
    private Integer firstAirDateYear;
    private Integer page;
    private String language;

    Search(String apiKey) {
    	this.apiKey = apiKey;
    }

    public Search type(Type searchType) {
    	this.type = searchType.getText();
    	return this;
    }

    public Search query(String query) {
    	this.query = query;
    	return this;
    }

    public Search firstAidDateYear(Integer firstAirDateYear) {
    	this.firstAirDateYear = firstAirDateYear;
    	return this;
    }

    public Search page(Integer page) {
    	this.page = page;
    	return this;
    }

    public Search language(String language) {
    	this.language = language;
    	return this;
    }
    
    public Response submit() {
		RestTemplate restTemplate = getRestTemplate();
	
		try {
		    String responseBody = restTemplate.getForEntity(build(), String.class).getBody();
		    Response results = readResults(responseBody);
		    return results;
		} catch (HttpClientErrorException e) {
		    Response results = readResults(e.getResponseBodyAsString());
		    return results;
		}
    }
    
    public String getApiKey() {
		return apiKey;
	}

	public String getType() {
		return type;
	}

	public String getQuery() {
		return query;
	}

	public Integer getFirstAirDateYear() {
		return firstAirDateYear;
	}

	public Integer getPage() {
		return page;
	}

	public String getLanguage() {
		return language;
	}

	protected RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
    private String build() {
		StringBuilder sb = new StringBuilder("");
		sb.append(DOMAIN);
		sb.append("/search/").append(this.type);
		sb.append("?api_key=").append(this.apiKey);
		sb.append("&query=").append(this.query);
		if (this.firstAirDateYear != null) {
		    sb.append("&first_air_date_year=").append(this.firstAirDateYear);
		}
		if (this.page != null) {
		    sb.append("&page=").append(this.page);
		}
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		return sb.toString();
    }
    
    private Response readResults(String responseBody) {
		Response results;
		try {
			results = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
				.readValue(responseBody, Response.class);
		} catch (Exception e) {
			results = new Response();
			results.setStatusCode(500);
			results.setStatusMessage("Failed to parse the response body");
			results.setSuccess(false);
		}
		return results;
    }

}
