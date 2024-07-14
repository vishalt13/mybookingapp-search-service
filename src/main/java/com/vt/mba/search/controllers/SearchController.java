package com.vt.mba.search.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vt.mba.search.exceptions.BusinessException;
import com.vt.mba.search.models.Movie;
import com.vt.mba.search.models.MovieResponse;
import com.vt.mba.search.models.Offer;
import com.vt.mba.search.models.OfferResponse;
import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.models.TheatreResponse;
import com.vt.mba.search.services.MovieService;
import com.vt.mba.search.services.OfferService;
import com.vt.mba.search.services.TheatreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchController {

	public static final String LOG_SEARCH_CONTROLLER = "[SearchController] - {}";

	private final TheatreService theatreService;
	private final MovieService movieService;
	private final OfferService offerService;

	@GetMapping("/theatres/{city}")
	public ResponseEntity<TheatreResponse> getTheatreList(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@RequestHeader(name = "version", required = true) String version,
			@RequestHeader(name = "messageId", required = true) String messageId,
			@PathVariable(name = "city", required = true) String city,
			@RequestParam(name = "movie", required = false) String movie,
			@RequestParam(name = "date", required = false) String date) {

		log.info(LOG_SEARCH_CONTROLLER, "Get Theatre List");
		List<Theatre> theatreList = theatreService.getTheatreListResponse(city, movie, date);

		if (CollectionUtils.isEmpty(theatreList)) {
			log.warn(LOG_SEARCH_CONTROLLER, "No movie found given criteria");
			throw new BusinessException(HttpStatus.NOT_FOUND, "404-01-001", "No Theatre found for given criteria");
		}

		TheatreResponse theareResponse = new TheatreResponse(theatreList);
		return new ResponseEntity<>(theareResponse, HttpStatus.OK);
	}

	@GetMapping("/movies/{city}")
	public ResponseEntity<MovieResponse> getMovieList(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@RequestHeader(name = "version", required = true) String version,
			@RequestHeader(name = "messageId", required = true) String messageId,
			@PathVariable(name = "city", required = true) String city,
			@RequestParam(name = "language", required = false) String lang,
			@RequestParam(name = "genre", required = false) String genre) {

		log.info(LOG_SEARCH_CONTROLLER, "Get Theatre List");
		List<Movie> movies = movieService.getMovies(city, lang, genre);

		if (CollectionUtils.isEmpty(movies)) {
			log.warn(LOG_SEARCH_CONTROLLER, "No movie found given criteria");
			throw new BusinessException(HttpStatus.NOT_FOUND, "404-01-002", "No movie found given criteria");
		}
		MovieResponse movieResponse = new MovieResponse();
		movieResponse.setMovies(movies);
		return new ResponseEntity<>(movieResponse, HttpStatus.OK);
	}

	@GetMapping(" /theatres/offer/{offerId}")
	public ResponseEntity<TheatreResponse> getTheatreListWithOffer(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@RequestHeader(name = "version", required = true) String version,
			@RequestHeader(name = "messageId", required = true) String messageId,
			@PathVariable(name = "offerId", required = true) String offerId) {

		log.info(LOG_SEARCH_CONTROLLER, "Get Theatre List with OfferId : " + offerId);
		List<Theatre> theatreList = offerService.getTheatreListWithOffer(offerId);

		if (CollectionUtils.isEmpty(theatreList)) {
			log.warn(LOG_SEARCH_CONTROLLER, "No Theatre found with given OfferId : " + offerId);
			throw new BusinessException(HttpStatus.NOT_FOUND, "404-01-003",
					"No Theatre found with given OfferId : " + offerId);
		}

		TheatreResponse theareResponse = new TheatreResponse(theatreList);
		return new ResponseEntity<>(theareResponse, HttpStatus.OK);
	}

	@GetMapping("/offers/{city}/{theatreId}")
	public ResponseEntity<OfferResponse> getOffersForCityTheatre(
			@RequestHeader(name = "Authorization", required = true) String authorization,
			@RequestHeader(name = "version", required = true) String version,
			@RequestHeader(name = "messageId", required = true) String messageId,
			@PathVariable(name = "city", required = true) String city,
			@RequestParam(name = "theatreId", required = false) String theatreId) {

		log.info(LOG_SEARCH_CONTROLLER, "Get Theatre List");
		List<Offer> offers = offerService.getOffersForCityTheatre(city, theatreId);

		if (CollectionUtils.isEmpty(offers)) {
			log.warn(LOG_SEARCH_CONTROLLER, "No offers found with given city and theatre");
			throw new BusinessException(HttpStatus.NOT_FOUND, "404-01-004",
					"No Theatre found with given OfferId : " + offers);
		}

		OfferResponse offerResponse = new OfferResponse(offers);
		return new ResponseEntity<>(offerResponse, HttpStatus.OK);
	}
}
