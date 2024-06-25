package com.vt.mba.search.controllers;

import java.util.List;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vt.mba.search.models.TheatreResponse;
import com.vt.mba.search.services.SearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

	public static final String LOG_SEARCH_CONTROLLER = "[SearchController] - {}";
	private final SearchService searchService;
	
	@GetMapping("/theatres/{city}")
	public ResponseEntity<TheatreResponse> getTheatreList(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@RequestHeader(name = "version", required = true) String version,
			@RequestHeader(name = "messageId", required = true) String messageId,
			@PathVariable(name = "city", required = true) String city,
			@RequestParam(name="movie") String movie,
			@RequestParam(name="date") String date) {
		
		log.info(LOG_SEARCH_CONTROLLER, "Get Theatre List");
		TheatreResponse theareResponse = searchService.getTheatreListResponse(city, movie, date);
		return new ResponseEntity<>(theareResponse, HttpStatus.OK);
	}
}
