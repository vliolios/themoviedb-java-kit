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
		assertThat("The API key doesn't match", new API("abc").search().tv().query("westworld").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", new API("abc").search().movie().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", new API("abc").search().company().query("lucas").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", new API("abc").search().collection().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", new API("abc").search().keyword().query("matrix").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", new API("abc").search().people().query("cooper").build().getApiKey(), equalTo("abc"));
		assertThat("The API key doesn't match", new API("abc").search().multi().query("matrix").build().getApiKey(), equalTo("abc"));
	}
}
