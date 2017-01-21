package com.vliolios.tmdb;

import java.util.List;

public class TVResult extends Result {

    private String firstAirDate;
    private List<String> originCountry;
    private String name;
    private String originalName;
	
    public String getFirstAirDate() {
		return firstAirDate;
	}
	public void setFirstAirDate(String firstAirDate) {
		this.firstAirDate = firstAirDate;
	}
	public List<String> getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(List<String> originCountry) {
		this.originCountry = originCountry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
    
    

}
