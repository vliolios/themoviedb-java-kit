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
		assertThat("The API key doesn't match", api.search(TVSearch.class), instanceOf(TVSearch.class));
		assertThat("The API key doesn't match", api.search(MovieSearch.class).getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search(MovieSearch.class), instanceOf(MovieSearch.class));
	}
}
