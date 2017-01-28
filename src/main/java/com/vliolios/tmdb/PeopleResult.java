package com.vliolios.tmdb;

import java.util.List;

public class PeopleResult extends Result {
	
	private String profilePath;
	private Boolean adult;
	private String name;
	private Double popularity;
	private List<KnownFor> knownFor;
	
	public String getProfilePath() {
		return profilePath;
	}
	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	public Boolean getAdult() {
		return adult;
	}
	public void setAdult(Boolean adult) {
		this.adult = adult;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPopularity() {
		return popularity;
	}
	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}
	public List<KnownFor> getKnownFor() {
		return knownFor;
	}
	public void setKnownFor(List<KnownFor> knownFor) {
		this.knownFor = knownFor;
	}
	
}
