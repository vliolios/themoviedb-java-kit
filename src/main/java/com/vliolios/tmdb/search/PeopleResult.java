package com.vliolios.tmdb.search;

import java.util.List;

import com.vliolios.tmdb.Result;

public class PeopleResult extends Result implements MultiResult {
	
	private String profilePath;
	private Boolean adult;
	private String name;
	private Double popularity;
	private List<MultiResult> knownFor;
	
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
	public List<MultiResult> getKnownFor() {
		return knownFor;
	}
	public void setKnownFor(List<MultiResult> knownFor) {
		this.knownFor = knownFor;
	}
	public boolean isMovie() {
		return false;
	}
	public boolean isTV() {
		return false;
	}
	public boolean isPerson() {
		return true;
	}
	public MovieResult asMovie() {
		return null;
	}
	public TVResult asTV() {
		return null;
	}
	public PeopleResult asPerson() {
		return this;
	}
	
}
