package com.vt.mba.search.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vt.mba.search.models.Movie;
import com.vt.mba.search.services.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {

	public static final String LOG_MOVIE_SERVICE = "[MovieService] - {}";

	@Override
	public List<Movie> getMovies(String city, String lang, String genre) {
		// TODO Auto-generated method stub
		return null;
	}

}
