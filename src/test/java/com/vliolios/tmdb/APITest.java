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
		assertThat("The API key doesn't match", api.search().tv().query("westworld").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().movie().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().company().query("lucas").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().collection().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().keyword().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().people().query("cooper").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", api.search().multi().query("matrix").build().getApiKey(), equalTo("abc"));
	}
}
