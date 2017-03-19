package com.vliolios.tmdb.search;


import java.io.IOException;

public class MovieSearch extends Search {
	
	private String language;
	private Boolean includeAdult;
	private String region;
	private Integer year;
	private Integer  primaryReleaseYear;

	private MovieSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<MovieResult> submit() {
		try {
			return getSearchService().movie(getApiKey(), getQuery(), getPage(), language, includeAdult, region, year, primaryReleaseYear).execute().body();
		} catch (IOException e) {
			Response<MovieResult> invalidResponse = new Response<>();
			invalidResponse.setStatusCode(500);
			invalidResponse.setStatusMessage("Failed to parse the response body");
			invalidResponse.setSuccess(false);
			return invalidResponse;
		}
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, String baseUrl) {
		return new Builder(apiKey, baseUrl);
	}

	public String getLanguage() {
		return language;
	}
	
	public Boolean getIncludeAdult() {
		return includeAdult;
	}

	public String getRegion() {
		return region;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getPrimaryReleaseYear() {
		return primaryReleaseYear;
	}

	@Override
	public String getType() {
		return "movie";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		MovieSearch movieSearch;

		private Builder(String apiKey, String baseUrl) {
			this.movieSearch = new MovieSearch(apiKey, baseUrl);
		}

		public Builder query(String query) {
			movieSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			movieSearch.page(page);
			return this;
		}

		public Builder language(String language) {
			movieSearch.language = language;
			return this;
		}

		public Builder includeAdult(Boolean includeAdult) {
			movieSearch.includeAdult = includeAdult;
			return this;
		}

		public Builder region(String region) {
			movieSearch.region = region;
			return this;
		}

		public Builder year(Integer year) {
			movieSearch.year = year;
			return this;
		}

		public Builder primaryReleaseYear(Integer primaryReleaseYear) {
			movieSearch.primaryReleaseYear = primaryReleaseYear;
			return this;
		}

		public MovieSearch build() {
			return movieSearch;
		}
	}

}
