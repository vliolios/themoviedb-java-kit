package com.vliolios.tmdb.search;

public class CompanySearch extends Search<CompanySearch, CompanyResult> {

	public CompanySearch(String apiKey) {
		super(apiKey);
	}

	@Override
	public String getType() {
		return "company";
	}

	@Override
	public CompanySearch getThis() {
		return this;
	}

	@Override
	public Class<CompanyResult> getResponseType() {
		return CompanyResult.class;
	}

}
