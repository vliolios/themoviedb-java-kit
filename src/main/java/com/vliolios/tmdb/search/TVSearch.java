package com.vliolios.tmdb.search;

public class TVSearch extends Search<TVSearch, TVResult> {
	
	private String language;
	private Integer firstAirDateYear;

	public TVSearch(String apiKey) {
		super(apiKey);
	}
	
	public TVSearch language(String language) {
    	this.language = language;
    	return this;
    }
	
	public TVSearch firstAidDateYear(Integer firstAirDateYear) {
    	this.firstAirDateYear = firstAirDateYear;
    	return this;
    }
	
	public String getLanguage() {
		return language;
	}
	
	public Integer getFirstAirDateYear() {
		return firstAirDateYear;
	}

	@Override
	protected String build() {
		StringBuilder sb = new StringBuilder(super.build());
		if (this.language != null) {
		    sb.append("&language=").append(this.language);
		}
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
