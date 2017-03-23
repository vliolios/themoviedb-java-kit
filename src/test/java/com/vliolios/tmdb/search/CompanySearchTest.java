package com.vliolios.tmdb.search;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CompanySearchTest extends WireMockTest {

	@Test
	public void testSubmitResponseSuccessful() {
		stub("/search/company", "search-company-success.json");
		Response<CompanyResult> response = CompanySearch.apiConfig(apiConfig).query("lucas").page(0).build().submit();

		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(1));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(1));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		CompanyResult result = response.getResults().get(0);
		assertThat("The id in the response's result is incorrect", result.getId(), is(1));
		assertThat("The name in the response's result is incorrect", result.getName(), is("Lucasfilm"));
		assertThat("The logo path in the response's result is incorrect", result.getLogoPath(), is("/8rUnVMVZjlmQsJ45UGotD0Uznxj.png"));
	}
	
	@Test
	public void testSubmitResponseWithError() {
		stub("/search/company", "search-error.json");
		Response<CompanyResult> response = CompanySearch.apiConfig(apiConfig).query("lucas").page(0).build().submit();

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
		stub("/search/company", "search-invalid.json");
		Response<CompanyResult> response = CompanySearch.apiConfig(apiConfig).query("lucas").build().submit();

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
		CompanySearch search = CompanySearch.apiConfig(apiConfig).query("lucas").build();
		assertThat("The query is incorrect", search.getQuery(), is("lucas"));
	}

	@Test
	public void testPage() {
		CompanySearch search = CompanySearch.apiConfig(apiConfig).query("lucas").page(1).build();
		assertThat("The page is incorrect", search.getPage(), is(1));
	}

	@Test
	public void testGetType() {
		CompanySearch search = CompanySearch.apiConfig(apiConfig).query("lucas").build();
		assertThat("The type is incorrect", search.getType(), is("company"));
	}

}
