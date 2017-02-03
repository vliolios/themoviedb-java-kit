package com.vliolios.tmdb.search;

import java.util.List;

import com.vliolios.tmdb.Result;

public class WatchableResult extends Result {

	private String posterPath;
    private Double popularity;
    private String backdropPath;
    private Double voteAverage;
    private String overview;
    private List<String> genreIds;
    private String originalLanguage;
    private Integer voteCount;
    
    public String getPosterPath() {
    	return posterPath;
    }

    public void setPosterPath(String posterPath) {
    	this.posterPath = posterPath;
    }

    public Double getPopularity() {
    	return popularity;
    }

    public void setPopularity(Double popularity) {
    	this.popularity = popularity;
    }

    public String getBackdropPath() {
    	return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
    	this.backdropPath = backdropPath;
    }

    public Double getVoteAverage() {
    	return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
    	this.voteAverage = voteAverage;
    }

    public String getOverview() {
    	return overview;
    }

    public void setOverview(String overview) {
    	this.overview = overview;
    }

    public List<String> getGenreIds() {
    	return genreIds;
    }

    public void setGenreIds(List<String> genreIds) {
    	this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
    	return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
    	this.originalLanguage = originalLanguage;
    }

    public Integer getVoteCount() {
    	return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
    	this.voteCount = voteCount;
    }

}
