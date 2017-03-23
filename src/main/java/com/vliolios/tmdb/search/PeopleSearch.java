package com.vliolios.tmdb.search;

public class PeopleSearch extends Search {
	
	private String language;
	private Boolean includeAdult;
	private String region;

	private PeopleSearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<PeopleResult> submit() {
		return submit(searchService -> searchService.person(getApiKey(), getQuery(), getPage(), language, includeAdult, region));
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

	@Override
	public String getType() {
		return "person";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		PeopleSearch multiSearch;

		private Builder(String apiKey, String baseUrl) {
			this.multiSearch = new PeopleSearch(apiKey, baseUrl);
		}

		public Builder query(String query) {
			multiSearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			multiSearch.page(page);
			return this;
		}

		public Builder language(String language) {
			multiSearch.language = language;
			return this;
		}

		public Builder includeAdult(Boolean includeAdult) {
			multiSearch.includeAdult = includeAdult;
			return this;
		}

		public Builder region(String region) {
			multiSearch.region = region;
			return this;
		}

		public PeopleSearch build() {
			return multiSearch;
		}
	}

}
