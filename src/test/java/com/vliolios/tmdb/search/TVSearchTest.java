package com.vliolios.tmdb.search;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TVSearchTest extends WireMockTest {

	@Test
	public void testSubmitResponseSuccessful() {
		stub("/search/tv", "search-tv-success.json");
		Response<TVResult> response = TVSearch.apiKey("abc", baseUrl).query("matrix").page(0).language("en").firstAirDateYear(2000).build().submit();

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
		stub("/search/tv", "search-error.json");
		Response<TVResult> response = TVSearch.apiKey("abc", baseUrl).query("matrix").page(0).language("en").firstAirDateYear(2000).build().submit();

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
		stub("/search/tv", "search-invalid.json");
		Response<TVResult> response = TVSearch.apiKey("abc", baseUrl).query("matrix").build().submit();

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
		TVSearch search = TVSearch.apiKey("abc", baseUrl).query("westworld").build();

		assertThat("The query is incorrect", search.getQuery(), is("westworld"));
	}

	@Test
	public void testPage() {
		TVSearch search = TVSearch.apiKey("abc", baseUrl).query("westworld").page(1).build();

		assertThat("The page is incorrect", search.getPage(), is(1));
	}

	@Test
	public void testLanguage() {
		TVSearch search = TVSearch.apiKey("abc", baseUrl).query("westworld").language("en").build();

		assertThat("The language is incorrect", search.getLanguage(), is("en"));
	}

	@Test
	public void testFirstAidDateYear() {
		TVSearch search = TVSearch.apiKey("abc", baseUrl).query("westworld").firstAirDateYear(2000).build();

		assertThat("The first air date year is incorrect", search.getFirstAirDateYear(), equalTo(2000));
	}
	
	@Test
	public void testGetType() {
		TVSearch search = TVSearch.apiKey("abc", baseUrl).query("westworld").build();
		
		assertThat("The type is incorrect", search.getType(), is("tv"));
	}

}
