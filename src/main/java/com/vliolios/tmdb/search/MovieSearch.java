package com.vliolios.tmdb.search;


import com.vliolios.tmdb.APIConfig;

public class MovieSearch extends Search {
	
	private String language;
	private Boolean includeAdult;
	private String region;
	private Integer year;
	private Integer  primaryReleaseYear;

	private MovieSearch(APIConfig apiConfig) {
		super(apiConfig);
	}

	public Response<MovieResult> submit() {
		return submit(searchService -> searchService.movie(getApiKey(), getQuery(), getPage(), language, includeAdult, region, year, primaryReleaseYear));
	}

	public static SearchWithQuery<Builder> apiConfig(APIConfig apiConfig) {
		return new Builder(apiConfig);
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

		private Builder(APIConfig apiConfig) {
			this.movieSearch = new MovieSearch(apiConfig);
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
