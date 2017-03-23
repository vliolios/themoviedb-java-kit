package com.vliolios.tmdb.search;

public class TVSearch extends Search {
	
	private String language;
	private Integer firstAirDateYear;

	private TVSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, String baseUrl) {
		return new TVSearch.Builder(apiKey, baseUrl);
	}
	
	public String getLanguage() {
		return language;
	}
	
	public Integer getFirstAirDateYear() {
		return firstAirDateYear;
	}


	@Override
	public Response<TVResult> submit() {
		return submit(searchService -> searchService.tv(getApiKey(), getQuery(), getPage(), language, firstAirDateYear));
	}

	@Override
	public String getType() {
		return "tv";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		TVSearch tvSearch;

		private Builder(String apiKey, String baseUrl) {
			this.tvSearch = new TVSearch(apiKey, baseUrl);
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
