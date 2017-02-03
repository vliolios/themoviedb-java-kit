package com.vliolios.tmdb.search;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.anyString;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.vliolios.tmdb.search.Response;
import com.vliolios.tmdb.search.TVResult;
import com.vliolios.tmdb.search.TVSearch;

public class TVSearchTest {
	
	RestTemplate restTemplate = mock(RestTemplate.class); 
	
	private static final String SEARCH_TV_RESPONSE_JSON_SUCCESS = "{" + 
			"  \"page\": 1," + 
			"  \"results\": [" + 
			"    {" + 
			"      \"poster_path\": \"/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg\"," + 
			"      \"popularity\": 29.780826," + 
			"      \"id\": 1399," + 
			"      \"backdrop_path\": \"/mUkuc2wyV9dHLG0D0Loaw5pO2s8.jpg\"," + 
			"      \"vote_average\": 7.91," + 
			"      \"overview\": \"Seven noble families fight for control of the mythical land of Westeros\"," + 
			"      \"first_air_date\": \"2011-04-17\"," + 
			"      \"origin_country\": [" + 
			"        \"US\"" + 
			"      ]," + 
			"      \"genre_ids\": [" + 
			"        10765," + 
			"        10759," + 
			"        18" + 
			"      ]," + 
			"      \"original_language\": \"en\"," + 
			"      \"vote_count\": 1172," + 
			"      \"name\": \"Game of Thrones\"," + 
			"      \"original_name\": \"Game of Thrones\"" + 
			"    }" + 
			"  ]," + 
			"  \"total_results\": 1," + 
			"  \"total_pages\": 1" + 
			"}";
	
	private static final String SEARCH_TV_RESPONSE_JSON_ERROR = "{" + 
			"  \"status_message\": \"Invalid API key: You must be granted a valid key.\"," + 
			"  \"success\": false," + 
			"  \"status_code\": 7" + 
			"}";

	@Test
	public void testQuery() {
		TVSearch search = new TVSearch("abc");
		search.query("westworld");
		
		assertThat("The query is incorrect", search.getQuery(), equalTo("westworld"));
	}

	@Test
	public void testPage() {
		TVSearch search = new TVSearch("abc");
		search.page(1);
		
		assertThat("The page is incorrect", search.getPage(), equalTo(1));
	}

	@Test
	public void testLanguage() {
		TVSearch search = new TVSearch("abc");
		search.language("en");
		
		assertThat("The language is incorrect", search.getLanguage(), equalTo("en"));
	}

	@Test
	public void testFirstAidDateYear() {
		TVSearch search = new TVSearch("abc");
		search.firstAidDateYear(2000);
		
		assertThat("The first air date year is incorrect", search.getFirstAirDateYear(), equalTo(2000));
	}
	
	@Test
	public void testSubmitResponseSuccessful() {
		TVSearch search = new TVSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<String>(SEARCH_TV_RESPONSE_JSON_SUCCESS, HttpStatus.OK));		
		Response<TVResult> response = search.query("matrix").page(0).language("en").firstAidDateYear(2000).submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/tv?api_key=abc&query=matrix&page=0&language=en&first_air_date_year=2000", String.class);
		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(1));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(1));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		TVResult result = response.getResults().get(0);
		assertThat("The poster path in the response's result is incorrect", result.getPosterPath(), is("/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg"));
		assertThat("The popularity in the response's result is incorrect", result.getPopularity(), is(29.780826));
		assertThat("The id in the response's result is incorrect", result.getId(), is(1399));
		assertThat("The backdrop path in the response's result is incorrect", result.getBackdropPath(), is("/mUkuc2wyV9dHLG0D0Loaw5pO2s8.jpg"));
		assertThat("The vore average in the response's result is incorrect", result.getVoteAverage(), is(7.91));
		assertThat("The overview in the response's result is incorrect", result.getOverview(), is("Seven noble families fight for control of the mythical land of Westeros"));
		assertThat("The first air date in the response's result is incorrect", result.getFirstAirDate(), is("2011-04-17"));
		assertThat("The origin country in the response's result is incorrect", result.getOriginCountry(), contains("US"));
		assertThat("The genre ids in the response's result are incorrect", result.getGenreIds(), contains("10765", "10759", "18"));
		assertThat("The oiginal language in the response's result is incorrect", result.getOriginalLanguage(), is("en"));
		assertThat("The vote count in the response's result is incorrect", result.getVoteCount(), is(1172));
		assertThat("The name in the response's result is incorrect", result.getName(), is("Game of Thrones"));
		assertThat("Theoriginal name in the response's result is incorrect", result.getOriginalName(), is("Game of Thrones"));
	}
	
	@Test
	public void testSubmitResponseWithError() {
		TVSearch search = new TVSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Unauthorized", SEARCH_TV_RESPONSE_JSON_ERROR.getBytes(), Charset.forName("UTF-8")));		
		Response<TVResult> response = search.query("matrix").page(0).language("en").firstAidDateYear(2000).submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/tv?api_key=abc&query=matrix&page=0&language=en&first_air_date_year=2000", String.class);
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
		TVSearch search = new TVSearch("abc") {
			@Override
			protected RestTemplate getRestTemplate() {
				return restTemplate;
			}
		};
		
		when(restTemplate.getForEntity(anyString(), eq(String.class))).thenReturn(new ResponseEntity<String>("invalid json", HttpStatus.OK));		
		Response<TVResult> response = search.query("matrix").submit();
		
		verify(restTemplate, times(1)).getForEntity("https://api.themoviedb.org/3/search/tv?api_key=abc&query=matrix", String.class);
		assertThat("The page value in the response is incorrect", response.getPage(), nullValue());
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), nullValue());
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), nullValue());
		assertThat("The size of the results in the response is incorrect", response.getResults(), nullValue());
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), is("Failed to parse the response body"));
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), is(500));
		assertThat("The success value in the response is incorrect", response.getSuccess(), is(false));
	}
	
	@Test
	public void testGetType() {
		TVSearch search = new TVSearch("abc");
		
		assertThat("The type is incorrect", search.getType(), equalTo("tv"));
	}

}
