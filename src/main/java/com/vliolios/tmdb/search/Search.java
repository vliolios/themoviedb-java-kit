package com.vliolios.tmdb.search;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vliolios.tmdb.APIConfig;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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


    protected <T> Response submit(Function<SearchService, Call<Response<T>>> searchServiceExecution) {
	    try {
		    return searchServiceExecution.apply(getSearchService()).execute().body();
	    } catch (IOException e) {
		    Response<T> invalidResponse = new Response<>();
		    invalidResponse.setStatusCode(500);
		    invalidResponse.setStatusMessage("Failed to parse the response body");
		    invalidResponse.setSuccess(false);
		    return invalidResponse;
	    }
    }

	protected SearchService getSearchService() {
		ObjectMapper mapper = getObjectMapper();

		OkHttpClient okClient = getOkHttpClient(mapper);

		Retrofit retrofit = getRetrofit(mapper, okClient);
		return retrofit.create(SearchService.class);
	}

	private Retrofit getRetrofit(ObjectMapper mapper, OkHttpClient okClient) {
		return new Retrofit.Builder()
					.baseUrl(apiConfig.getBaseUrl())
					.client(okClient)
					.addConverterFactory(JacksonConverterFactory.create(mapper))
					.build();
	}

	private OkHttpClient getOkHttpClient(final ObjectMapper mapper) {
		return new OkHttpClient.Builder().addInterceptor(chain -> {
			okhttp3.Response response = chain.proceed(chain.request());

			JsonNode root = mapper.readTree(response.body().string());
			if (root.get("results") != null) {
				for (JsonNode resultNode : root.get("results")) {
					if (!resultNode.has("media_type")) {
						((ObjectNode) resultNode).put("media_type", getType());
					}
				}
			}
			return response.newBuilder().body(ResponseBody.create(MediaType.parse("application/json"), new ObjectMapper().writeValueAsString(root))).build();
		}).build();
	}

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
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
