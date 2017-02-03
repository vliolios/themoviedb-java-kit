package com.vliolios.tmdb.search;

public class CollectionSearch extends Search<CollectionSearch, CollectionResult> {

	private String language;
	
	public CollectionSearch(String apiKey) {
		super(apiKey);
	}
	
	public CollectionSearch language(String language) {
    	this.language = language;
    	return this;
    }
	
	public String getLanguage() {
		return language;
	}
	
	

	@Override
	protected String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		return sb.toString();
	}

	@Override
	public String getType() {
		return "collection";
	}

	@Override
	public CollectionSearch getThis() {
		return this;
	}

	@Override
	public Class<CollectionResult> getResponseType() {
		return CollectionResult.class;
	}

}
