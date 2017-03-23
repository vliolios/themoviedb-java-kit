package com.vliolios.tmdb.search;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.vliolios.tmdb.APIConfig;
import org.junit.Before;
import org.junit.Rule;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.fail;

public class WireMockTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().dynamicPort());

	protected String baseUrl;
	protected APIConfig apiConfig;

	@Before
	public void setWireMockRuleBaseUrlUp() {
		baseUrl = "http://localhost:" + wireMockRule.port();
		apiConfig = new APIConfig("abc", baseUrl);
	}

	protected void stub(String urlPath, String responseFileName) {
		try {
			String response = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(responseFileName).toURI())));
			stubFor(get(urlPathEqualTo(urlPath)).willReturn(aResponse().withHeader("Content-Type", "application/json")
					.withBody(response)));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
