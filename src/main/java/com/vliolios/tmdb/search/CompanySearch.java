package com.vliolios.tmdb.search;

import com.vliolios.tmdb.APIConfig;

public class CompanySearch extends Search {

	private CompanySearch(APIConfig apiConfig) {
		super(apiConfig);
	}

	public Response<CompanyResult> submit() {
		return submit(searchService -> searchService.company(getApiKey(), getQuery(), getPage()));
	}

	public static SearchWithQuery<Builder> apiConfig(APIConfig apiConfig   ) {
		return new Builder(apiConfig);
	}

	@Override
	public String getType() {
		return "company";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		CompanySearch companySearch;

		private Builder(APIConfig apiConfig) {
			this.companySearch = new CompanySearch(apiConfig);
		}

		public Builder query(String query) {
			companySearch.query(query);
			return this;
		}

		public Builder page(Integer page) {
			companySearch.page(page);
			return this;
		}

		public CompanySearch build() {
			return companySearch;
		}

	}

}
