package com.vliolios.tmdb;


public class MovieSearch extends Search<MovieSearch, MovieResult> {
	
	private Boolean includeAdult;
	private String region;
	private Integer year;
	private Integer  primaryReleaseYear;

	MovieSearch(String apiKey) {
		super(apiKey);
	}
	
	public MovieSearch includeAdult(Boolean includeAdult) {
		this.includeAdult = includeAdult;
		return this;
	}

	public MovieSearch region(String region) {
		this.region = region;
		return this;
	}
	
	public MovieSearch year(Integer year) {
		this.year = year;
		return this;
	}
	
	public MovieSearch primaryReleaseYear(Integer primaryReleaseYear) {
		this.primaryReleaseYear = primaryReleaseYear;
		return this;
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
	public MovieSearch getThis() {
		return this;
	}

	@Override
	public Class<MovieResult> getResponseType() {
		return MovieResult.class;
	}

}
