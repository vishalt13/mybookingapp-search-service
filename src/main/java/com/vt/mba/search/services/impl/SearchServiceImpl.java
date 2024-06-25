package com.vt.mba.search.services.impl;

import org.springframework.stereotype.Service;

import com.vt.mba.search.models.TheatreResponse;
import com.vt.mba.search.services.SearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

	@Override
	public TheatreResponse getTheatreListResponse(String city, String movie, String date) {
		
		return null;
	}

}
