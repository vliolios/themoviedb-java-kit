package com.vliolios.tmdb;

public class TVSearch extends Search<TVSearch, TVResult> {
	
	private Integer firstAirDateYear;

	public TVSearch(String apiKey) {
		super(apiKey);
	}
	
	public TVSearch firstAidDateYear(Integer firstAirDateYear) {
    	this.firstAirDateYear = firstAirDateYear;
    	return this;
    }
	
	public Integer getFirstAirDateYear() {
		return firstAirDateYear;
	}

	@Override
	protected String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.firstAirDateYear != null) {
		    sb.append("&first_air_date_year=").append(this.firstAirDateYear);
		}
		return sb.toString();
	}

	@Override
	public String getType() {
		return "tv";
	}

	@Override
	public TVSearch getThis() {
		return this;
	}

	@Override
	public Class<TVResult> getResponseType() {
		return TVResult.class;
	}
	
	

}
