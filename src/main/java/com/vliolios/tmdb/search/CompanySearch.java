package com.vliolios.tmdb.search;

import org.springframework.web.client.RestTemplate;

public class CompanySearch extends Search<CompanyResult> {

	private CompanySearch(String apiKey, RestTemplate restTemplate) {
		super(apiKey, restTemplate);
	}

	public static SearchWithQuery apiKey(String apiKey, RestTemplate restTemplate) {
		return new Builder(apiKey, restTemplate);
	}

	@Override
	public String getType() {
		return "company";
	}

	@Override
	public Class<CompanyResult> getResponseType() {
		return CompanyResult.class;
	}

	public static class Builder implements SearchWithQuery {
		CompanySearch companySearch;

		private Builder(String apiKey, RestTemplate restTemplate) {
			this.companySearch = new CompanySearch(apiKey, restTemplate);
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

	public interface SearchWithQuery {
		Builder query(String query);
	}

}
