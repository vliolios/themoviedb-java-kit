package com.vliolios.tmdb.search;

import com.vliolios.tmdb.Result;

public class CompanyResult extends Result {
	
	private String logoPath;
	private String name;
	
	public String getLogoPath() {
		return logoPath;
	}
	
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	

}
