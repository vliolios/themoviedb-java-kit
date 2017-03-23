package com.vliolios.tmdb.search;

import com.vliolios.tmdb.APIConfig;

public class MultiSearch extends Search {
	
	private String language;
	private Boolean includeAdult;
	private String region;

	private MultiSearch(APIConfig apiConfig) {
		super(apiConfig);
	}

	public Response<MultiResult> submit() {
		return submit(searchService -> searchService.multi(getApiKey(), getQuery(), getPage(), language, includeAdult, region));
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

	@Override
	public String getType() {
		return "multi";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		MultiSearch multiSearch;

		private Builder(APIConfig apiConfig) {
			this.multiSearch = new MultiSearch(apiConfig);
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

		public MultiSearch build() {
			return multiSearch;
		}
	}
}
