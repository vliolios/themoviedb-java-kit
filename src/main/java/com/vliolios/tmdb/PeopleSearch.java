package com.vliolios.tmdb;

public class PeopleSearch extends Search<PeopleSearch, PeopleResult> {
	
	private String language;
	private Boolean includeAdult;
	private String region;

	public PeopleSearch(String apiKey) {
		super(apiKey);
	}
	
	public PeopleSearch language(String language) {
    	this.language = language;
    	return this;
    }
	
	public PeopleSearch includeAdult(Boolean includeAdult) {
		this.includeAdult = includeAdult;
		return this;
	}

	public PeopleSearch region(String region) {
		this.region = region;
		return this;
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
	public String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
		if (includeAdult != null) {
			sb.append("&include_adult=").append(includeAdult.toString());
		}
		if (region != null) {
			sb.append("&region=").append(region);
		}
		return sb.toString();
		
	}

	@Override
	public String getType() {
		return "person";
	}

	@Override
	public PeopleSearch getThis() {
		return this;
	}

	@Override
	public Class<PeopleResult> getResponseType() {
		return PeopleResult.class;
	}

}
