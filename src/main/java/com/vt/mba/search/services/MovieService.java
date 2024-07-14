package com.vt.mba.search.services;

import java.util.List;

import com.vt.mba.search.models.Movie;

public interface MovieService {

	List<Movie> getMovies(String city, String lang, String genre);
}
