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
		assertThat("The API key doesn't match", API.search("abc").tv().query("westworld").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", API.search("abc").movie().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", API.search("abc").company().query("lucas").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", API.search("abc").collection().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", API.search("abc").keyword().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", API.search("abc").people().query("cooper").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", API.search("abc").multi().query("matrix").build().getApiKey(), equalTo("abc"));
	}
}
