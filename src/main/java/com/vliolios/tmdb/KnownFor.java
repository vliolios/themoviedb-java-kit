package com.vliolios.tmdb;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "media_type")
	@JsonSubTypes({
	    @Type(value = MovieResult.class, name = "movie"),
	    @Type(value = TVResult.class, name = "tv") })
public interface KnownFor {
    
    public boolean isMovie();
    public boolean isTV();
    public MovieResult asMovie();
    public TVResult asTV();
}
