package com.vliolios.tmdb.search;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "media_type")
	@JsonSubTypes({
	    @Type(value = MovieResult.class, name = "movie"),
	    @Type(value = TVResult.class, name = "tv"),
	    @Type(value = PeopleResult.class, name = "person")})
public interface MultiResult {
    
    public boolean isMovie();
    public boolean isTV();
    public boolean isPerson();
    public MovieResult asMovie();
    public TVResult asTV();
    public PeopleResult asPerson();
}
