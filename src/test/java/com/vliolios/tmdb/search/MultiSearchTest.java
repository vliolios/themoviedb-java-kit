package com.vliolios.tmdb.search;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MultiSearchTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);
	
	private static final String SEARCH_MULTI_RESPONSE_JSON_SUCCESS = "	{\n" + 
			"		\"page\": 1,\n" + 
			"		\"results\": [{\n" + 
			"			\"poster_path\": null,\n" + 
			"			\"adult\": false,\n" + 
			"			\"overview\": \"A TV concert special showcasing performances by Alice Cooper and support acts Sha Na Na, The Tubes and Nazareth. Filmed June 19, 1977 at Anaheim Stadium, Anaheim, CA, this special was broadcast in September 1977 on US television.\",\n" + 
			"			\"release_date\": \"\",\n" + 
			"			\"original_title\": \"Alice Cooper & Friends\",\n" + 
			"			\"genre_ids\": [],\n" + 
			"			\"id\": 204854,\n" + 
			"			\"media_type\": \"movie\",\n" + 
			"			\"original_language\": \"en\",\n" + 
			"			\"title\": \"Alice Cooper & Friends\",\n" + 
			"			\"backdrop_path\": null,\n" + 
			"			\"popularity\": 1.000005,\n" + 
			"			\"vote_count\": 0,\n" + 
			"			\"video\": false,\n" + 
			"			\"vote_average\": 0\n" + 
			"		}, {\n" + 
			"			\"poster_path\": \"/sq8KWFV8CO9uISVPYaB0oMf2ds0.jpg\",\n" + 
			"			\"popularity\": 1.473036,\n" + 
			"			\"id\": 63260,\n" + 
			"			\"overview\": \"Young ensemble comedy about a group of friends living together for the first time. Each episode is told from the perspective of Cooper Barrett, a wickedly charming and deeply flawed recent college grad whose life is filled with terrible choices, excellent mistakes and fantastic misadventures.\",\n" + 
			"			\"backdrop_path\": \"/t5r21lOzZeWResIsGKTTtmtY2vg.jpg\",\n" + 
			"			\"vote_average\": 4.5,\n" + 
			"			\"media_type\": \"tv\",\n" + 
			"			\"first_air_date\": \"2016-01-03\",\n" + 
			"			\"origin_country\": [\n" + 
			"				\"US\"\n" + 
			"			],\n" + 
			"			\"genre_ids\": [\n" + 
			"				35\n" + 
			"			],\n" + 
			"			\"original_language\": \"en\",\n" + 
			"			\"vote_count\": 4,\n" + 
			"			\"name\": \"Cooper Barrett's Guide to Surviving Life\",\n" + 
			"			\"original_name\": \"Cooper Barrett's Guide to Surviving Life\"\n" + 
			"		}, {\n" + 
			"			\"profile_path\": \"/z4eKEtwZXVespbZCS2qjYZaztyv.jpg\",\n" + 
			"			\"adult\": false,\n" + 
			"			\"id\": 55470,\n" + 
			"			\"media_type\": \"person\",\n" + 
			"			\"known_for\": [{\n" + 
			"				\"poster_path\": \"/dlIPGXPxXQTp9kFrRzn0RsfUelx.jpg\",\n" + 
			"				\"adult\": false,\n" + 
			"				\"overview\": \"Predominantly set during World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.\",\n" + 
			"				\"release_date\": \"2011-07-22\",\n" + 
			"				\"original_title\": \"Captain America: The First Avenger\",\n" + 
			"				\"genre_ids\": [\n" + 
			"					28,\n" + 
			"					12,\n" + 
			"					878\n" + 
			"				],\n" + 
			"				\"id\": 1771,\n" + 
			"				\"media_type\": \"movie\",\n" + 
			"				\"original_language\": \"en\",\n" + 
			"				\"title\": \"Captain America: The First Avenger\",\n" + 
			"				\"backdrop_path\": \"/pmZtj1FKvQqISS6iQbkiLg5TAsr.jpg\",\n" + 
			"				\"popularity\": 7.545604,\n" + 
			"				\"vote_count\": 5103,\n" + 
			"				\"video\": false,\n" + 
			"				\"vote_average\": 6.5\n" + 
			"			}, {\n" + 
			"				\"poster_path\": \"/4oy4e0DP6LRwRszfx8NY8EYBj8V.jpg\",\n" + 
			"				\"adult\": false,\n" + 
			"				\"overview\": \"Vlad Tepes is a great hero, but when he learns the Sultan is preparing for battle and needs to form an army of 1,000 boys, including Vlad's son, he vows to find a way to protect his family. Vlad turns to dark forces in order to get the power to destroy his enemies and agrees to go from hero to monster as he's turned into the mythological vampire Dracula.\",\n" + 
			"				\"release_date\": \"2014-10-01\",\n" + 
			"				\"original_title\": \"Dracula Untold\",\n" + 
			"				\"genre_ids\": [\n" + 
			"					27,\n" + 
			"					28,\n" + 
			"					18,\n" + 
			"					14,\n" + 
			"					10752\n" + 
			"				],\n" + 
			"				\"id\": 49017,\n" + 
			"				\"media_type\": \"movie\",\n" + 
			"				\"original_language\": \"en\",\n" + 
			"				\"title\": \"Dracula Untold\",\n" + 
			"				\"backdrop_path\": \"/6UPlIYKxZqUR6Xbpgu1JKG0J7UC.jpg\",\n" + 
			"				\"popularity\": 6.608555,\n" + 
			"				\"vote_count\": 1703,\n" + 
			"				\"video\": false,\n" + 
			"				\"vote_average\": 6.2\n" + 
			"			}, {\n" + 
			"				\"poster_path\": \"/ckrTPz6FZ35L5ybjqvkLWzzSLO7.jpg\",\n" + 
			"				\"adult\": false,\n" + 
			"				\"overview\": \"The peaceful realm of Azeroth stands on the brink of war as its civilization faces a fearsome race of invaders: orc warriors fleeing their dying home to colonize another. As a portal opens to connect the two worlds, one army faces destruction and the other faces extinction. From opposing sides, two heroes are set on a collision course that will decide the fate of their family, their people, and their home.\",\n" + 
			"				\"release_date\": \"2016-05-25\",\n" + 
			"				\"original_title\": \"Warcraft\",\n" + 
			"				\"genre_ids\": [\n" + 
			"					12,\n" + 
			"					14,\n" + 
			"					28\n" + 
			"				],\n" + 
			"				\"id\": 68735,\n" + 
			"				\"media_type\": \"movie\",\n" + 
			"				\"original_language\": \"en\",\n" + 
			"				\"title\": \"Warcraft\",\n" + 
			"				\"backdrop_path\": \"/5SX2rgKXZ7NVmAJR5z5LprqSXKa.jpg\",\n" + 
			"				\"popularity\": 6.117948,\n" + 
			"				\"vote_count\": 1274,\n" + 
			"				\"video\": false,\n" + 
			"				\"vote_average\": 6.2\n" + 
			"			}],\n" + 
			"			\"name\": \"Dominic Cooper\",\n" + 
			"			\"popularity\": 5.345584\n" + 
			"		}],\n" + 
			"		\"total_results\": 3,\n" + 
			"		\"total_pages\": 1\n" + 
			"	}";
	
	private static final String SEARCH_MULTI_RESPONSE_JSON_ERROR = "{" + 
			"  \"status_message\": \"Invalid API key: You must be granted a valid key.\"," + 
			"  \"success\": false," + 
			"  \"status_code\": 7" + 
			"}";

	private String baseUrl = "http://localhost:8090";

	@Test
	public void testSubmitResponseSuccessful() {
		stubFor(get(urlPathEqualTo("/search/multi")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody(SEARCH_MULTI_RESPONSE_JSON_SUCCESS)));
		Response<MultiResult> response = MultiSearch.apiKey("abc", baseUrl).query("matrix").page(0).language("en").includeAdult(true).region("US").build().submit();
		
		assertThat("The page value in the response is incorrect", response.getPage(), is(1));
		assertThat("The total pages value in the response is incorrect", response.getTotalPages(), is(1));
		assertThat("The total results value in the response is incorrect", response.getTotalResults(), is(3));
		assertThat("The size of the results in the response is incorrect", response.getResults(), hasSize(3));
		assertThat("The status message value in the response is incorrect", response.getStatusMessage(), nullValue());
		assertThat("The status code value in the response is incorrect", response.getStatusCode(), nullValue());
		assertThat("The success value in the response is incorrect", response.getSuccess(), nullValue());
		
		MultiResult movieResult = response.getResults().get(0);
		assertThat("The first result in the response is a person", movieResult.isPerson(), is(false));
		assertThat("The first result in the response is a tv show", movieResult.isTV(), is(false));
		assertThat("The first result in the response is not a movie", movieResult.isMovie(), is(true));
		assertThat("The first result in the response is a person", movieResult.asPerson(), nullValue());
		assertThat("The first result in the response is a tv show", movieResult.asTV(), nullValue());
		assertThat("The title in the response's result is incorrect", movieResult.asMovie().getTitle(), is("Alice Cooper & Friends"));
		
		MultiResult tvResult = response.getResults().get(1);
		assertThat("The second result in the response is a person", tvResult.isPerson(), is(false));
		assertThat("The second result in the response is a movie", tvResult.isMovie(), is(false));
		assertThat("The second result in the response is a not tv show", tvResult.isTV(), is(true));
		assertThat("The second result in the response is a person", tvResult.asPerson(), nullValue());
		assertThat("The second result in the response is a movie", tvResult.asMovie(), nullValue());
		assertThat("The name in the response's result is incorrect", tvResult.asTV().getName(), is("Cooper Barrett's Guide to Surviving Life"));
		
		MultiResult personResult = response.getResults().get(2);
		assertThat("The third result in the response is a tv show", personResult.isTV(), is(false));
		assertThat("The third result in the response is a movie", personResult.isMovie(), is(false));
		assertThat("The third result in the response is a not person", personResult.isPerson(), is(true));
		assertThat("The third result in the response is a tv show", personResult.asTV(), nullValue());
		assertThat("The third result in the response is a movie", personResult.asMovie(), nullValue());
		assertThat("The name in the response's result is incorrect", personResult.asPerson().getName(), is("Dominic Cooper"));
		
	}
	
	@Test
	public void testSubmitResponseWithError() {
		stubFor(get(urlPathEqualTo("/search/multi")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody(SEARCH_MULTI_RESPONSE_JSON_ERROR)));
		Response<MultiResult> response = MultiSearch.apiKey("abc", baseUrl).query("matrix").page(0).language("en").includeAdult(true).region("US").build().submit();
		
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
		stubFor(get(urlPathEqualTo("/search/multi")).willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withBody("invalid json")));
		Response<MultiResult> response = MultiSearch.apiKey("abc", baseUrl).query("matrix").build().submit();
		
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
		MultiSearch search = MultiSearch.apiKey("abc", baseUrl).query("matrix").build();
		assertThat("The query is incorrect", search.getQuery(), is("matrix"));
	}
	
	@Test
	public void testPage() {
		MultiSearch search = MultiSearch.apiKey("abc", baseUrl).query("matrix").page(1).build();
		assertThat("The page is incorrect", search.getPage(), is(1));
	}
	
	@Test
	public void testLanguage() {
		MultiSearch search = MultiSearch.apiKey("abc", baseUrl).query("matrix").language("en").build();
		assertThat("The language is incorrect", search.getLanguage(), is("en"));
	}

	@Test
	public void testIncludeAdult() {
		MultiSearch search = MultiSearch.apiKey("abc", baseUrl).query("matrix").includeAdult(true).build();
		assertThat("The include adult flag is incorrect", search.getIncludeAdult(), is(true));
	}

	@Test
	public void testRegion() {
		MultiSearch search = MultiSearch.apiKey("abc", baseUrl).query("matrix").region("US").build();
		assertThat("The region is incorrect", search.getRegion(), is("US"));
	}

	@Test
	public void testGetType() {
		MultiSearch search = MultiSearch.apiKey("abc", baseUrl).query("matrix").build();
		assertThat("The type is incorrect", search.getType(), is("multi"));
	}

}
