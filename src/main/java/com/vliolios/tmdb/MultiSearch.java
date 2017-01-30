package com.vliolios.tmdb;

public class MultiSearch extends Search<MultiSearch, Polymorphic> {
	
	private String language;
	private Boolean includeAdult;
	private String region;

	public MultiSearch(String apiKey) {
		super(apiKey);
		// TODO Auto-generated constructor stub
	}
	
	public MultiSearch language(String language) {
    	this.language = language;
    	return this;
    }
	
	public MultiSearch includeAdult(Boolean includeAdult) {
		this.includeAdult = includeAdult;
		return this;
	}

	public MultiSearch region(String region) {
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
		return "multi";
	}

	@Override
	public MultiSearch getThis() {
		return this;
	}

	@Override
	public Class<Polymorphic> getResponseType() {
		return Polymorphic.class;
	}

}
