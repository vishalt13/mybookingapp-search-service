package com.vt.mba.search.services;

import java.util.List;

import com.vt.mba.search.models.Theatre;

public interface TheatreService {

	List<Theatre> getTheatreListResponse(String city, String movie, String date);
}
