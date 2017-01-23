/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vliolios.tmdb;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author vliolios
 */
public class APITest {

	@Test
	public void testSearch() {
		API api = new API("abc");
		assertThat("The API key doesn't match", api.search(TVSearch.class).getApiKey(), equalTo("abc"));
		assertThat("The search object ius not an instance of the right type", api.search(TVSearch.class), instanceOf(TVSearch.class));
		assertThat("The API key doesn't match", api.search(MovieSearch.class).getApiKey(), equalTo("abc"));
		assertThat("The search object ius not an instance of the right type", api.search(MovieSearch.class), instanceOf(MovieSearch.class));
		assertThat("The API key doesn't match", api.search(CompanySearch.class).getApiKey(), equalTo("abc"));
		assertThat("The search object ius not an instance of the right type", api.search(CompanySearch.class), instanceOf(CompanySearch.class));
		assertThat("The API key doesn't match", api.search(CollectionSearch.class).getApiKey(), equalTo("abc"));
		assertThat("The search object ius not an instance of the right type", api.search(CollectionSearch.class), instanceOf(CollectionSearch.class));
	}
}
