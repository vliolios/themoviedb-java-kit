package com.vliolios.tmdb.search;

import com.vliolios.tmdb.APIConfig;

public class TVSearch extends Search {
	
	private String language;
	private Integer firstAirDateYear;

	private TVSearch(APIConfig apiConfig) {
		super(apiConfig);
	}

	public static SearchWithQuery<Builder> apiConfig(APIConfig apiConfig) {
		return new TVSearch.Builder(apiConfig);
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

		private Builder(APIConfig apiConfig) {
			this.tvSearch = new TVSearch(apiConfig);
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
