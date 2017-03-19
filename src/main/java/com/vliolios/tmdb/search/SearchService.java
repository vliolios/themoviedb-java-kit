package com.vliolios.tmdb.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

	@GET("search/tv")
	Call<Response<TVResult>> tv(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page,
	                            @Query("language") String language, @Query("first_air_date_year") Integer firstAirDateYear);

	@GET("search/company")
	Call<Response<CompanyResult>> company(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page);

	@GET("search/collection")
	Call<Response<CollectionResult>> collection(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page,
	                                            @Query("language") String language);

	@GET("search/keyword")
	Call<Response<KeywordResult>> keyword(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page);

	@GET("search/movie")
	Call<Response<MovieResult>> movie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page,
	                                  @Query("language") String language, @Query("include_adult") Boolean includeAdult,
	                                  @Query("region") String region, @Query("year") Integer year,
	                                  @Query("primary_release_year") Integer primaryReleaseYear);

	@GET("search/multi")
	Call<Response<MultiResult>> multi(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page,
	                                  @Query("language") String language, @Query("include_adult") Boolean includeAdult,
	                                  @Query("region") String region);

	@GET("search/person")
	Call<Response<PeopleResult>> person(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") Integer page,
	                                    @Query("language") String language, @Query("include_adult") Boolean includeAdult,
	                                    @Query("region") String region);

	@GET("api")
	Call<String> api();

}
