package com.vliolios.tmdb.search;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MovieSearchTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);

	private static final String SEARCH_MOVIE_RESPONSE_JSON_SUCCESS = "{\n" +
			"	\"page\": 1," + 
			"  \"results\": [" + 
			"    {" + 
			"      \"poster_path\": \"/pMdTc3kYCD1869UX6cdYUT8Xe49.jpg\",\n" +
			"      \"adult\": false,\n" + 
			"      \"overview\": \"Feature-length documentary about the rise of Marvel Studios and their films leading up to, and including, The Avengers\",\n" +
			"      \"release_date\": \"2012-09-25\",\n" + 
			"      \"genre_ids\": [\n" +
			"        99\n" + 
			"      ],\n" + 
			"      \"id\": 161097,\n" +
			"      \"original_title\": \"Marvel Studios: Building a Cinematic Universe\",\n" + 
			"      \"original_language\": \"en\",\n" +
			"      \"title\": \"Building a Cinematic Universe\",\n" + 
			"      \"backdrop_path\": \"/yeKT2gNFxHGbTT3Htj5PE9IerGJ.jpg\",\n" +
			"      \"popularity\": 1.136598,\n" +
			"      \"vote_count\": 4,\n" +
			"      \"video\": false,\n" + 
			"      \"vote_average\": 3.88\n" +
			"    }\n" + 
			"  ],\n" + 
			"  \"total_results\": 1,\n" + 
			"  \"total_pages\": 1\n" + 
			"}";
	
	private static final String SEARCH_MOVIE_RESPONSE_JSON_ERROR = "{" + 
			"  \"status_message\": \"Invalid API key: You must be granted a valid key.\"," + 
			"  \"success\": false," + 
			"  \"status_code\": 7" + 
			"}";

	private String baseUrl = "http://localhost:8090";
	
	@Test
	public void testSubmitResponseSuccessful() {
		stubFor(get(urlPathEqualTo("/search/movie")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody(SEARCH_MOVIE_RESPONSE_JSON_SUCCESS)));
		Response<MovieResult> response = MovieSearch.apiKey("abc", baseUrl).query("matrix").page(0).language("en").includeAdult(true).region("US").year(2000).primaryReleaseYear(1990).build().submit();

		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(1));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(1));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		MovieResult result = response.getResults().get(0);
		assertThat("The poster path in the response's result is incorrect", result.getPosterPath(), is("/pMdTc3kYCD1869UX6cdYUT8Xe49.jpg"));
		assertThat("The popularity in the response's result is incorrect", result.getPopularity(), is(1.136598));
		assertThat("The id in the response's result is incorrect", result.getId(), is(161097));
		assertThat("The backdrop path in the response's result is incorrect", result.getBackdropPath(), is("/yeKT2gNFxHGbTT3Htj5PE9IerGJ.jpg"));
		assertThat("The vore average in the response's result is incorrect", result.getVoteAverage(), is(3.88));
		assertThat("The overview in the response's result is incorrect", result.getOverview(), is("Feature-length documentary about the rise of Marvel Studios and their films leading up to, and including, The Avengers"));
		assertThat("The genre ids in the response's result are incorrect", result.getGenreIds(), contains("99"));
		assertThat("The oiginal language in the response's result is incorrect", result.getOriginalLanguage(), is("en"));
		assertThat("The adult flag in the response's result is incorrect", result.getVoteCount(), is(4));
		assertThat("The vore average in the response's result is incorrect", result.getAdult(), is(false));
		assertThat("The release date in the response's result is incorrect", result.getReleaseDate(), is("2012-09-25"));
		assertThat("The original title in the response's result is incorrect", result.getOriginalTitle(), is("Marvel Studios: Building a Cinematic Universe"));
		assertThat("The title in the response's result is incorrect", result.getTitle(), is("Building a Cinematic Universe"));
		assertThat("The video flag in the response's result is incorrect", result.getVideo(), is(false));
		
	}
	
	@Test
	public void testSubmitResponseWithError() {
		stubFor(get(urlPathEqualTo("/search/movie")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody(SEARCH_MOVIE_RESPONSE_JSON_ERROR)));
		Response<MovieResult> response = MovieSearch.apiKey("abc", baseUrl).query("matrix").page(0).language("en").includeAdult(true).region("US").year(2000).primaryReleaseYear(1990).build().submit();

		assertThat("The page value in the response is incorrect", response.getPage(), nullValue());
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), nullValue());
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), nullValue());
		assertThat("The size of the results in the response is incorrect", response.getResults(), nullValue());
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), is("Invalid API key: You must be granted a valid key."));
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), is(7));
		assertThat("The success value in the response is incorrect", response.getSuccess(), is(false));
	}
	
	@Test
	public void testSubmitResponseInvalid() {
		stubFor(get(urlPathEqualTo("/search/movie")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody("invalid json")));
		Response<MovieResult> response = MovieSearch.apiKey("abc", baseUrl).query("matrix").build().submit();

		assertThat("The page value in the response is incorrect", response.getPage(), nullValue());
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), nullValue());
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), nullValue());
		assertThat("The size of the results in the response is incorrect", response.getResults(), nullValue());
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), is("Failed to parse the response body"));
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), is(500));
		assertThat("The success value in the response is incorrect", response.getSuccess(), is(false));
	}
	
	@Test
	public void testQuery() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").build();
		assertThat("The query is incorrect", search.getQuery(), is("matrix"));
	}
	
	@Test
	public void testPage() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").page(1).build();
		assertThat("The page is incorrect", search.getPage(), is(1));
	}
	
	@Test
	public void testLanguage() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").language("en").build();
		assertThat("The language is incorrect", search.getLanguage(), is("en"));
	}

	@Test
	public void testIncludeAdult() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").includeAdult(true).build();
		assertThat("The include adult flag is incorrect", search.getIncludeAdult(), is(true));
	}

	@Test
	public void testRegion() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").region("US").build();
		assertThat("The region is incorrect", search.getRegion(), is("US"));
	}

	@Test
	public void testYear() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").year(2000).build();
		assertThat("The year is incorrect", search.getYear(), is(2000));
	}

	@Test
	public void testPrimaryReleaseYear() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").primaryReleaseYear(2000).build();
		assertThat("The primary release year is incorrect", search.getPrimaryReleaseYear(), is(2000));
	}
	
	@Test
	public void testGetType() {
		MovieSearch search = MovieSearch.apiKey("abc", baseUrl).query("matrix").build();
		assertThat("The type is incorrect", search.getType(), is("movie"));
	}

}
