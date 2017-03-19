package com.vliolios.tmdb.search;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public abstract class Search {

	private String baseUrl;
    private String apiKey;
    private String query;
    private Integer page;

    Search(String apiKey, String baseUrl) {
    	this.apiKey = apiKey;
    	this.baseUrl = baseUrl;
    }

	protected void query(String query) {
    	this.query = query;
    }

	protected void page(Integer page) {
    	this.page = page;
    }
    
    public abstract Response submit();

	protected SearchService getSearchService() {
		ObjectMapper mapper = getObjectMapper();

		OkHttpClient okClient = getOkHttpClient(mapper);

		Retrofit retrofit = getRetrofit(mapper, okClient);
		return retrofit.create(SearchService.class);
	}

	private Retrofit getRetrofit(ObjectMapper mapper, OkHttpClient okClient) {
		return new Retrofit.Builder()
					.baseUrl(baseUrl)
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
		return apiKey;
	}

	public String getQuery() {
		return query;
	}

	public Integer getPage() {
		return page;
	}
    
    public abstract String getType();
}
