package com.vliolios.tmdb.search;

import org.springframework.web.client.RestTemplate;

public class TVSearch extends Search<TVResult> {
	
	private String language;
	private Integer firstAirDateYear;

	private TVSearch(String apiKey, RestTemplate restTemplate) {
		super(apiKey, restTemplate);
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, RestTemplate restTemplate) {
		return new TVSearch.Builder(apiKey, restTemplate);
	}
	
	public String getLanguage() {
		return language;
	}
	
	public Integer getFirstAirDateYear() {
		return firstAirDateYear;
	}

	@Override
	protected String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		if (this.firstAirDateYear != null) {
		    sb.append("&first_air_date_year=").append(this.firstAirDateYear);
		}
		return sb.toString();
	}

	@Override
	public String getType() {
		return "tv";
	}

	@Override
	public Class<TVResult> getResponseType() {
		return TVResult.class;
	}

	public static class Builder implements SearchWithQuery<Builder> {
		TVSearch tvSearch;

		private Builder(String apiKey, RestTemplate restTemplate) {
			this.tvSearch = new TVSearch(apiKey, restTemplate);
		}

		public Builder query(String query) {
			tvSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			tvSearch.page(page);
			return this;
		}

		public Builder language(String language) {
			tvSearch.language = language;
			return this;
		}

		public Builder firstAirDateYear(Integer firstAirDateYear) {
			tvSearch.firstAirDateYear = firstAirDateYear;
			return this;
		}

		public TVSearch build() {
			return tvSearch;
		}

	}
}
