package com.vliolios.tmdb.search;

public class CompanySearch extends Search {

	private CompanySearch(String apiKey, String baseUrl) {
		super(apiKey, baseUrl);
	}

	public Response<CompanyResult> submit() {
		return submit(searchService -> searchService.company(getApiKey(), getQuery(), getPage()));
	}

	public static SearchWithQuery<Builder> apiKey(String apiKey, String baseUrl) {
		return new Builder(apiKey, baseUrl);
	}

	@Override
	public String getType() {
		return "company";
	}

	public static class Builder implements SearchWithQuery<Builder> {
		CompanySearch companySearch;

		private Builder(String apiKey, String baseUrl) {
			this.companySearch = new CompanySearch(apiKey, baseUrl);
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
