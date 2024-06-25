package com.vt.mba.search.services;

import com.vt.mba.search.models.TheatreResponse;

public interface SearchService {

	TheatreResponse getTheatreListResponse(String city, String movie, String date);
}
