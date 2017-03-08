package com.vliolios.tmdb.search;


import org.springframework.web.client.RestTemplate;

public class MovieSearch extends Search<MovieResult> {
	
	private String language;
	private Boolean includeAdult;
	private String region;
	private Integer year;
	private Integer  primaryReleaseYear;

	private MovieSearch(String apiKey, RestTemplate restTemplate) {
		super(apiKey, restTemplate);
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, RestTemplate restTemplate) {
		return new Builder(apiKey, restTemplate);
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
	public String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		if (includeAdult != null) {
			sb.append("&include_adult=").append(includeAdult.toString());
		}
		if (region != null) {
			sb.append("&region=").append(region);
		}
		if (year != null) {
			sb.append("&year=").append(year);
		}
		if (primaryReleaseYear != null) {
			sb.append("&primary_release_year=").append(primaryReleaseYear);
		}
		return sb.toString();
		
	}
	
	@Override
	public String getType() {
		return "movie";
	}

	@Override
	public Class<MovieResult> getResponseType() {
		return MovieResult.class;
	}

	public static class Builder implements SearchWithQuery<Builder> {
		MovieSearch movieSearch;

		private Builder(String apiKey, RestTemplate restTemplate) {
			this.movieSearch = new MovieSearch(apiKey, restTemplate);
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
