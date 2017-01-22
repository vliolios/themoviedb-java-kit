package com.vliolios.tmdb;

public class CompanySearch extends Search<CompanySearch, CompanyResult> {

	CompanySearch(String apiKey) {
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
