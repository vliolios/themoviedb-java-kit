package com.vliolios.tmdb;

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

public class PeopleSearchTest {
	
	RestTemplate restTemplate = mock(RestTemplate.class);
	
	private static final String SEARCH_PEOPLE_RESPONSE_JSON_SUCCESS = "{\n" + 
			"  \"page\": 1,\n" + 
			"  \"results\": [\n" + 
			"    {\n" + 
			"      \"profile_path\": \"/2daC5DeXqwkFND0xxutbnSVKN6c.jpg\",\n" + 
			"      \"adult\": false,\n" + 
			"      \"id\": 51329,\n" + 
			"      \"known_for\": [\n" + 
			"        {\n" + 
			"          \"poster_path\": \"/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg\",\n" + 
			"          \"adult\": false,\n" + 
			"          \"overview\": \"Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser.\",\n" + 
			"          \"release_date\": \"2014-07-30\",\n" + 
			"          \"original_title\": \"Guardians of the Galaxy\",\n" + 
			"          \"genre_ids\": [\n" + 
			"            28,\n" + 
			"            878,\n" + 
			"            12\n" + 
			"          ],\n" + 
			"          \"id\": 118340,\n" + 
			"          \"media_type\": \"movie\",\n" + 
			"          \"original_language\": \"en\",\n" + 
			"          \"title\": \"Guardians of the Galaxy\",\n" + 
			"          \"backdrop_path\": \"/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg\",\n" + 
			"          \"popularity\": 9.267731,\n" + 
			"          \"vote_count\": 5002,\n" + 
			"          \"video\": false,\n" + 
			"          \"vote_average\": 7.97\n" + 
			"        }," +
			" 		 {\n" + 
			"          \"poster_path\": \"/xn3QM6aInhQp631K2lXpGFox2Kc.jpg\",\n" + 
			"          \"popularity\": 6.605526,\n" + 
			"          \"id\": 60866,\n" + 
			"          \"overview\": \"A medical student who becomes a zombie joins a Coroner's Office in order to gain access to the brains she must reluctantly eat so that she can maintain her humanity. But every brain she eats, she also inherits their memories and must now solve their deaths with help from the Medical examiner and a police detective.\",\n" + 
			"          \"backdrop_path\": \"/d2YDPTQPe3mI2LqBWwb0CchN54f.jpg\",\n" + 
			"          \"vote_average\": 6.01,\n" + 
			"          \"media_type\": \"tv\",\n" + 
			"          \"first_air_date\": \"2015-03-17\",\n" + 
			"          \"origin_country\": [\n" + 
			"            \"US\"\n" + 
			"          ],\n" + 
			"          \"genre_ids\": [\n" + 
			"            27,\n" + 
			"            18,\n" + 
			"            80,\n" + 
			"            10765\n" + 
			"          ],\n" + 
			"          \"original_language\": \"en\",\n" + 
			"          \"vote_count\": 69,\n" + 
			"          \"name\": \"iZombie\",\n" + 
			"          \"original_name\": \"iZombie\"\n" + 
			"        }" +
			"      ],\n" + 
			"      \"name\": \"Bradley Hemmings\",\n" + 
			"      \"popularity\": 1.273\n" + 
			"    }\n" +
			"	],\n" + 
			"  \"total_results\": 1,\n" + 
			"  \"total_pages\": 1\n" + 
			"}";
	
	private static final String SEARCH_PEOPLE_RESPONSE_JSON_ERROR = "{" + 
			"  \"status_message\": \"Invalid API key: You must be granted a valid key.\"," + 
			"  \"success\": false," + 
			"  \"status_code\": 7" + 
			"}";
	
	@Test
	public void testSubmitResponseSuccessful() {
		PeopleSearch search = new PeopleSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<String>(SEARCH_PEOPLE_RESPONSE_JSON_SUCCESS, HttpStatus.OK));		
		Response<PeopleResult> response = search.query("matrix").page(0).language("en").includeAdult(true).region("US").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/person?api_key=abc&query=matrix&page=0&language=en&include_adult=true&region=US", String.class);
		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(1));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(1));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		PeopleResult result = response.getResults().get(0);
		assertThat("The poster path in the response's result is incorrect", result.getProfilePath(), is("/2daC5DeXqwkFND0xxutbnSVKN6c.jpg"));
		assertThat("The name in the response's result is incorrect", result.getName(), is("Bradley Hemmings"));
		assertThat("The popularity in the response's result is incorrect", result.getPopularity(), is(1.273));
		assertThat("The id in the response's result is incorrect", result.getId(), is(51329));
		assertThat("The vore average in the response's result is incorrect", result.getAdult(), is(false));
		
		MultiResult movieKnownFor = result.getKnownFor().get(0);
		assertThat("The first known-for item in the response's result is not a movie", movieKnownFor.isMovie(), is(true));
		assertThat("The poster path in the first known-for item in the response's result is incorrect", movieKnownFor.asMovie().getPosterPath(), is("/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg"));
		assertThat("The title in the first known-for item in the response's result is incorrect", movieKnownFor.asMovie().getTitle(), is("Guardians of the Galaxy"));
		
		MultiResult tvKnownFor = result.getKnownFor().get(1);
		assertThat("The second known-for item in the response's result is a movie", tvKnownFor.isTV(), is(true));
		assertThat("The scond known-for item in the response's result is not a tv show", tvKnownFor.asTV().getPosterPath(), is("/xn3QM6aInhQp631K2lXpGFox2Kc.jpg"));
		assertThat("The name in the second known-for item in the response's result is incorrect", tvKnownFor.asTV().getName(), is("iZombie"));
	}
	
	@Test
	public void testSubmitResponseWithError() {
		PeopleSearch search = new PeopleSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized", SEARCH_PEOPLE_RESPONSE_JSON_ERROR.getBytes(), Charset.forName("UTF-8")));			
		Response<PeopleResult> response = search.query("brad").page(0).language("en").includeAdult(true).region("US").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/person?api_key=abc&query=brad&page=0&language=en&include_adult=true&region=US", String.class);
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
		PeopleSearch search = new PeopleSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<String>("invalid json", HttpStatus.OK));		
		Response<PeopleResult> response = search.query("matrix").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/person?api_key=abc&query=matrix", String.class);
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
		PeopleSearch search = new PeopleSearch("abc");
		search.query("Brad");
		
		assertThat("The query is incorrect", search.getQuery(), equalTo("Brad"));
	}
	
	@Test
	public void testPage() {
		PeopleSearch search = new PeopleSearch("abc");
		search.page(1);
		
		assertThat("The page is incorrect", search.getPage(), equalTo(1));
	}
	
	@Test
	public void testLanguage() {
		PeopleSearch search = new PeopleSearch("abc");
		search.language("en");
		
		assertThat("The language is incorrect", search.getLanguage(), equalTo("en"));
	}

	@Test
	public void testIncludeAdult() {
		PeopleSearch search = new PeopleSearch("abc");
		search.includeAdult(true);
		
		assertThat("The include adult flag is incorrect", search.getIncludeAdult(), equalTo(true));
	}

	@Test
	public void testRegion() {
		PeopleSearch search = new PeopleSearch("abc");
		search.region("US");
		
		assertThat("The region is incorrect", search.getRegion(), equalTo("US"));
	}

}
