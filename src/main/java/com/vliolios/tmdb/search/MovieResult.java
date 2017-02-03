package com.vliolios.tmdb.search;

public class MovieResult extends WatchableResult implements MultiResult {

	
	public Boolean adult;
	public String releaseDate;
	public String originalTitle;
	public String title;
	public Boolean video;
	
	public Boolean getAdult() {
		return adult;
	}
	
	public void setAdult(Boolean adult) {
		this.adult = adult;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}
	
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getVideo() {
		return video;
	}
	
	public void setVideo(Boolean video) {
		this.video = video;
	}
    
    public boolean isMovie() {
    	return true;
    }
    
    public boolean isTV() {
    	return false;
    }
    
	public boolean isPerson() {
		return false;
	}
    
    public MovieResult asMovie() {
		return this; 
	}
    
    public TVResult asTV() {
    	return null;
    }
	
	public PeopleResult asPerson() {
		return null;
	}
}
