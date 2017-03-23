package com.vliolios.tmdb.search;

import com.vliolios.tmdb.APIConfig;
import retrofit2.Call;

import java.io.IOException;
import java.util.function.Function;

public abstract class Search {

	private APIConfig apiConfig;
    private String query;
    private Integer page;

	public Search(APIConfig apiConfig) {
		this.apiConfig = apiConfig;
	}

	protected void query(String query) {
    	this.query = query;
    }

	protected void page(Integer page) {
    	this.page = page;
    }
    
    public abstract Response submit();


    protected <T> Response submit(Function<SearchService, Call<Response<T>>> searchServiceCall) {
	    try {
		    return searchServiceCall.apply(new SearchServiceFactory().getSearchService(getType(), apiConfig)).execute().body();
	    } catch (IOException e) {
		    Response<T> invalidResponse = new Response<>();
		    invalidResponse.setStatusCode(500);
		    invalidResponse.setStatusMessage("Failed to parse the response body");
		    invalidResponse.setSuccess(false);
		    return invalidResponse;
	    }
    }

	public String getApiKey() {
		return apiConfig.getApiKey();
	}

	public String getQuery() {
		return query;
	}

	public Integer getPage() {
		return page;
	}
    
    public abstract String getType();
}
