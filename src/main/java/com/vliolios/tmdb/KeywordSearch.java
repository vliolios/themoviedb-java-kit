package com.vliolios.tmdb;

public class KeywordSearch extends Search<KeywordSearch, KeywordResult> {

	public KeywordSearch(String apiKey) {
		super(apiKey);
	}

	@Override
	public String getType() {
		return "keyword";
	}

	@Override
	public KeywordSearch getThis() {
		return this;
	}

	@Override
	public Class<KeywordResult> getResponseType() {
		return KeywordResult.class;
	}

}
