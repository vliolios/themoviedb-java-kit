package com.vliolios.tmdb.search;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.vliolios.tmdb.search.CollectionResult;
import com.vliolios.tmdb.search.CollectionSearch;
import com.vliolios.tmdb.search.Response;

public class CollectionSearchTest {
	
	RestTemplate restTemplate = mock(RestTemplate.class); 
	
	private static final String SEARCH_COLLECTION_RESPONSE_JSON_SUCCESS = "{\n" + 
			"  \"page\": 1,\n" + 
			"  \"results\": [\n" + 
			"    {\n" + 
			"      \"id\": 9485,\n" + 
			"      \"backdrop_path\": \"/z5A5W3WYJc3UVEWljSGwdjDgQ0j.jpg\",\n" + 
			"      \"name\": \"The Fast and the Furious Collection\",\n" + 
			"      \"poster_path\": \"/uv63yAGg1zETAs1XQsOQpava87l.jpg\"\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"total_pages\": 1,\n" + 
			"  \"total_results\": 1\n" + 
			"}";
	
	private static final String SEARCH_COLLECTION_RESPONSE_JSON_ERROR = "{" + 
			"  \"status_message\": \"Invalid API key: You must be granted a valid key.\"," + 
			"  \"success\": false," + 
			"  \"status_code\": 7" + 
			"}";
	
	@Test
	public void testSubmitResponseSuccessful() {
		CollectionSearch search = new CollectionSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<String>(SEARCH_COLLECTION_RESPONSE_JSON_SUCCESS, HttpStatus.OK));		
		Response<CollectionResult> response = search.query("star").page(0).language("en").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/collection?api_key=abc&query=star&page=0&language=en", String.class);
		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(1));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(1));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		CollectionResult result = response.getResults().get(0);
		assertThat("The name in the response's result is incorrect", result.getName(), is("The Fast and the Furious Collection"));
		assertThat("The backdrop path in the response's result is incorrect", result.getBackdropPath(), is("/z5A5W3WYJc3UVEWljSGwdjDgQ0j.jpg"));
		assertThat("The id in the response's result is incorrect", result.getId(), is(9485));
		assertThat("The poster path in the response's result is incorrect", result.getPosterPath(), is("/uv63yAGg1zETAs1XQsOQpava87l.jpg"));
	}
	
	@Test
	public void testSubmitResponseWithError() {
		CollectionSearch search = new CollectionSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized", SEARCH_COLLECTION_RESPONSE_JSON_ERROR.getBytes(), Charset.forName("UTF-8")));			
		Response<CollectionResult> response = search.query("star").page(0).language("en").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/collection?api_key=abc&query=star&page=0&language=en", String.class);
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
		CollectionSearch search = new CollectionSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<String>("invalid json", HttpStatus.OK));		
		Response<CollectionResult> response = search.query("star").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/collection?api_key=abc&query=star", String.class);
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
		CollectionSearch search = new CollectionSearch("abc");
		search.query("star wars");
		
		assertThat("The query is incorrect", search.getQuery(), equalTo("star wars"));
	}

	@Test
	public void testPage() {
		CollectionSearch search = new CollectionSearch("abc");
		search.page(1);
		
		assertThat("The page is incorrect", search.getPage(), equalTo(1));
	}
	
	@Test
	public void testLanguage() {
		CollectionSearch search = new CollectionSearch("abc");
		search.language("en");
		
		assertThat("The language is incorrect", search.getLanguage(), equalTo("en"));
	}

	@Test
	public void testGetType() {
		CollectionSearch search = new CollectionSearch("abc");
		
		assertThat("The type is incorrect", search.getType(), equalTo("collection"));
	}

}
