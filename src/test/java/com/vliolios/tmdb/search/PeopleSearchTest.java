package com.vliolios.tmdb.search;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PeopleSearchTest extends WireMockTest {

	@Test
	public void testSubmitResponseSuccessful() {
		stub("/search/person", "search-people-success.json");
		Response<PeopleResult> response = PeopleSearch.apiConfig(apiConfig).query("matrix").page(0).language("en").includeAdult(true).region("US").build().submit();

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
		stub("/search/person", "search-error.json");
		Response<PeopleResult> response = PeopleSearch.apiConfig(apiConfig).query("brad").page(0).language("en").includeAdult(true).region("US").build().submit();

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
		stub("/search/person", "search-invalid.json");
		Response<PeopleResult> response = PeopleSearch.apiConfig(apiConfig).query("matrix").build().submit();

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
		PeopleSearch search = PeopleSearch.apiConfig(apiConfig).query("Brad").build();
		assertThat("The query is incorrect", search.getQuery(), is("Brad"));
	}
	
	@Test
	public void testPage() {
		PeopleSearch search =PeopleSearch.apiConfig(apiConfig).query("Brad").page(1).build();
		assertThat("The page is incorrect", search.getPage(), is(1));
	}
	
	@Test
	public void testLanguage() {
		PeopleSearch search =PeopleSearch.apiConfig(apiConfig).query("Brad").language("en").build();
		assertThat("The language is incorrect", search.getLanguage(), is("en"));
	}

	@Test
	public void testIncludeAdult() {
		PeopleSearch search =PeopleSearch.apiConfig(apiConfig).query("Brad").includeAdult(true).build();
		assertThat("The include adult flag is incorrect", search.getIncludeAdult(), is(true));
	}

	@Test
	public void testRegion() {
		PeopleSearch search = PeopleSearch.apiConfig(apiConfig).query("Brad").region("US").build();
		assertThat("The region is incorrect", search.getRegion(), is("US"));
	}

}
