package com.vliolios.tmdb.search;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MultiSearchTest extends WireMockTest {

	@Test
	public void testSubmitResponseSuccessful() {
		stub("/search/multi", "search-multi-success.json");
		Response<MultiResult> response = MultiSearch.apiConfig(apiConfig).query("matrix").page(0).language("en").includeAdult(true).region("US").build().submit();
		
		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(3));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(3));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		MultiResult movieResult = response.getResults().get(0);
		assertThat("The first result in the response is a person", movieResult.isPerson(), is(false));
		assertThat("The first result in the response is a tv show", movieResult.isTV(), is(false));
		assertThat("The first result in the response is not a movie", movieResult.isMovie(), is(true));
		assertThat("The first result in the response is a person", movieResult.asPerson(), nullValue());
		assertThat("The first result in the response is a tv show", movieResult.asTV(), nullValue());
		assertThat("The title in the response's result is incorrect", movieResult.asMovie().getTitle(), is("Alice Cooper & Friends"));
		
		MultiResult tvResult = response.getResults().get(1);
		assertThat("The second result in the response is a person", tvResult.isPerson(), is(false));
		assertThat("The second result in the response is a movie", tvResult.isMovie(), is(false));
		assertThat("The second result in the response is a not tv show", tvResult.isTV(), is(true));
		assertThat("The second result in the response is a person", tvResult.asPerson(), nullValue());
		assertThat("The second result in the response is a movie", tvResult.asMovie(), nullValue());
		assertThat("The name in the response's result is incorrect", tvResult.asTV().getName(), is("Cooper Barrett's Guide to Surviving Life"));
		
		MultiResult personResult = response.getResults().get(2);
		assertThat("The third result in the response is a tv show", personResult.isTV(), is(false));
		assertThat("The third result in the response is a movie", personResult.isMovie(), is(false));
		assertThat("The third result in the response is a not person", personResult.isPerson(), is(true));
		assertThat("The third result in the response is a tv show", personResult.asTV(), nullValue());
		assertThat("The third result in the response is a movie", personResult.asMovie(), nullValue());
		assertThat("The name in the response's result is incorrect", personResult.asPerson().getName(), is("Dominic Cooper"));
		
	}
	
	@Test
	public void testSubmitResponseWithError() {
		stub("/search/multi", "search-error.json");
		Response<MultiResult> response = MultiSearch.apiConfig(apiConfig).query("matrix").page(0).language("en").includeAdult(true).region("US").build().submit();
		
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
		stub("/search/multi", "search-invalid.json");
		Response<MultiResult> response = MultiSearch.apiConfig(apiConfig).query("matrix").build().submit();
		
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
		MultiSearch search = MultiSearch.apiConfig(apiConfig).query("matrix").build();
		assertThat("The query is incorrect", search.getQuery(), is("matrix"));
	}
	
	@Test
	public void testPage() {
		MultiSearch search = MultiSearch.apiConfig(apiConfig).query("matrix").page(1).build();
		assertThat("The page is incorrect", search.getPage(), is(1));
	}
	
	@Test
	public void testLanguage() {
		MultiSearch search = MultiSearch.apiConfig(apiConfig).query("matrix").language("en").build();
		assertThat("The language is incorrect", search.getLanguage(), is("en"));
	}

	@Test
	public void testIncludeAdult() {
		MultiSearch search = MultiSearch.apiConfig(apiConfig).query("matrix").includeAdult(true).build();
		assertThat("The include adult flag is incorrect", search.getIncludeAdult(), is(true));
	}

	@Test
	public void testRegion() {
		MultiSearch search = MultiSearch.apiConfig(apiConfig).query("matrix").region("US").build();
		assertThat("The region is incorrect", search.getRegion(), is("US"));
	}

	@Test
	public void testGetType() {
		MultiSearch search = MultiSearch.apiConfig(apiConfig).query("matrix").build();
		assertThat("The type is incorrect", search.getType(), is("multi"));
	}

}
