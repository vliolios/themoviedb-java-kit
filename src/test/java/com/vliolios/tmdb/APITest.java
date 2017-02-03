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
		assertThat("The API key doesn't match", api.search().tv().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().movie().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().company().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().collection().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().keyword().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().people().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().multi().getApiKey(), equalTo("abc"));
	}
}
