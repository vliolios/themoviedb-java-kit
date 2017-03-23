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
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SearchServiceFactory {

	public SearchService getSearchService(String searchType, APIConfig apiConfig) {
		ObjectMapper mapper = getObjectMapper();
		OkHttpClient okClient = getOkHttpClient(mapper, searchType);
		Retrofit retrofit = getRetrofit(mapper, okClient, apiConfig);
		return retrofit.create(SearchService.class);
	}

	private Retrofit getRetrofit(ObjectMapper mapper, OkHttpClient okClient, APIConfig apiConfig) {
		return new Retrofit.Builder()
				.baseUrl(apiConfig.getBaseUrl())
				.client(okClient)
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.build();
	}

	private OkHttpClient getOkHttpClient(final ObjectMapper mapper, String searchType) {
		return new OkHttpClient.Builder().addInterceptor(chain -> {
			okhttp3.Response response = chain.proceed(chain.request());

			JsonNode root = mapper.readTree(response.body().string());
			if (root.get("results") != null) {
				for (JsonNode resultNode : root.get("results")) {
					if (!resultNode.has("media_type")) {
						((ObjectNode) resultNode).put("media_type", searchType);
					}
				}
			}
			return response.newBuilder().body(ResponseBody.create(MediaType.parse("application/json"), new ObjectMapper().writeValueAsString(root))).build();
		}).build();
	}

	private ObjectMapper getObjectMapper() {
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}
}
