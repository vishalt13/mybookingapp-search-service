package com.vt.mba.search.services;

import java.util.List;

import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.models.TheatreResponse;

public interface SearchService {

	TheatreResponse getTheatreListResponse(String city, String movie, String date);
}
