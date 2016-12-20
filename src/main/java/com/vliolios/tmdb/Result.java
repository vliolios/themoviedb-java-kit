/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vliolios.tmdb;

import java.util.List;

/**
 *
 * @author vliolios
 */
public class Result {

    private String posterPath;
    private Double popularity;
    private Integer id;
    private String backdropPath;
    private Double voteAverage;
    private String overview;
    private String firstAirDate;
    private List<String> originCountry;
    private List<String> genreIds;
    private String originalLanguage;
    private Integer voteCount;
    private String name;
    private String originalName;

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

    public Integer getId() {
    	return id;
    }

    public void setId(Integer id) {
    	this.id = id;
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

    @Override
    public String toString() {
    	return "\n\tResult{" + "\n\t\tposterPath=" + posterPath + ", \n\t\tpopularity=" + popularity + ", \n\t\tid=" + id + ", \n\t\tbackdropPath=" + backdropPath + ", \n\t\tvoteAverage=" + voteAverage + ", \n\t\toverview=" + overview + ", \n\t\tfirstAirDate=" + firstAirDate + ", \n\t\toriginCountry=" + originCountry + ", \n\t\tgenreIds=" + genreIds + ", \n\t\toriginalLanguage=" + originalLanguage + ", \n\t\tvoteCount=" + voteCount + ", \n\t\tname=" + name + ", \n\t\toriginalName=" + originalName + '}';
    }

}
