package com.vliolios.tmdb.search;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class KeywordSearchTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);

	private static final String SEARCH_KEYWORD_RESPONSE_JSON_SUCCESS = "{\n" + 
			"  \"page\": 1,\n" + 
			"  \"results\": [\n" + 
			"    {\n" + 
			"      \"id\": 9951,\n" + 
			"      \"name\": \"alien\"\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"total_pages\": 1,\n" + 
			"  \"total_results\": 1\n" + 
			"}";
	
	private static final String SEARCH_KEYWORD_RESPONSE_JSON_ERROR = "{" +
			"  \"status_message\": \"Invalid API key: You must be granted a valid key.\"," + 
			"  \"success\": false," + 
			"  \"status_code\": 7" + 
			"}";

	private String baseUrl = "http://localhost:8090";

	
	@Test
	public void testSubmitResponseSuccessful() {
		stubFor(get(urlPathEqualTo("/search/keyword")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody(SEARCH_KEYWORD_RESPONSE_JSON_SUCCESS)));

		Response<KeywordResult> response = KeywordSearch.apiKey("abc", baseUrl).query("alien").page(0).build().submit();

		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(1));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(1));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		KeywordResult result = response.getResults().get(0);
		assertThat("The id in the response's result is incorrect", result.getId(), is(9951));
		assertThat("The name in the response's result is incorrect", result.getName(), is("alien"));
	}
	
	@Test
	public void testSubmitResponseWithError() {
		stubFor(get(urlPathEqualTo("/search/keyword")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody(SEARCH_KEYWORD_RESPONSE_JSON_ERROR)));
		Response<KeywordResult> response = KeywordSearch.apiKey("abc", baseUrl).query("alien").page(0).build().submit();

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
		stubFor(get(urlPathEqualTo("/search/keyword")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody("invalid json")));
		Response<KeywordResult> response = KeywordSearch.apiKey("abc", baseUrl).query("alien").build().submit();

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
		KeywordSearch search = KeywordSearch.apiKey("abc", baseUrl).query("alien").build();
		assertThat("The query is incorrect", search.getQuery(), is("alien"));
	}

	@Test
	public void testPage() {
		KeywordSearch search = KeywordSearch.apiKey("abc", baseUrl).query("alien").page(1).build();
		assertThat("The page is incorrect", search.getPage(), is(1));
	}

	@Test
	public void testGetType() {
		KeywordSearch search = KeywordSearch.apiKey("abc", baseUrl).query("alien").build();
		assertThat("The type is incorrect", search.getType(), is("keyword"));
	}

}
