package com.vliolios.tmdb.search;

import com.vliolios.tmdb.Result;

public class CollectionResult extends Result {

	private String backdropPath;
	private String name;
	private String posterPath;
	
	public String getBackdropPath() {
		return backdropPath;
	}
	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	
	
}
