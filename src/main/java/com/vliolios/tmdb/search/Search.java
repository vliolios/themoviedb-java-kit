package com.vliolios.tmdb.search;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *
 * @author vliolios
 */
public abstract class Search<T extends Search<T,X>, X> { 
	
	private static final String DOMAIN = "https://api.themoviedb.org/3";

    private String apiKey;
    private String query;
    private Integer page;

    Search(String apiKey) {
    	this.apiKey = apiKey;
    }

    public T query(String query) {
    	this.query = query;
    	return getThis();
    }

    public T page(Integer page) {
    	this.page = page;
    	return getThis();
    }
    
    public Response<X> submit() {
		RestTemplate restTemplate = getRestTemplate();
	
		try {
		    String responseBody = restTemplate.getForEntity(build(), String.class).getBody();
		    Response<X> results = readResults(responseBody);
		    return results;
		} catch (HttpClientErrorException e) {
		    Response<X> results = readResults(e.getResponseBodyAsString());
		    return results;
		}
    }
    
    public String getApiKey() {
		return apiKey;
	}

	public String getQuery() {
		return query;
	}

	public Integer getPage() {
		return page;
	}

	protected RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
    protected String build() {
		StringBuilder sb = new StringBuilder("");
		sb.append(DOMAIN);
		sb.append("/search/").append(getType());
		sb.append("?api_key=").append(this.apiKey);
		sb.append("&query=").append(this.query);
		if (this.page != null) {
		    sb.append("&page=").append(this.page);
		}
		return sb.toString();
    }
    
    private Response<X> readResults(String responseBody) {
		Response<X> results;
		try {
			ObjectMapper mapper = new ObjectMapper()
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
					.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
			
			JsonNode root = mapper.readTree(responseBody);
			if (root.get("results") != null) {
				for (JsonNode resultNode : root.get("results")) {
					if (!((ObjectNode) resultNode).has("media_type")) {
						((ObjectNode) resultNode).put("media_type", getType());
					}
				}
			}

			JavaType type = mapper.getTypeFactory().constructParametricType(Response.class, getResponseType());
			results = mapper.readValue(mapper.writeValueAsString(root), type);
		} catch (Exception e) {
			results = new Response<X>();
			results.setStatusCode(500);
			results.setStatusMessage("Failed to parse the response body");
			results.setSuccess(false);
		}
		return results;
    }
    
    public abstract String getType();
    public abstract T getThis();
    public abstract Class<X> getResponseType();

}
