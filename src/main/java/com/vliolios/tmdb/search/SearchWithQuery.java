package com.vliolios.tmdb.search;

public interface SearchWithQuery<T> {
	T query(String query);
}
