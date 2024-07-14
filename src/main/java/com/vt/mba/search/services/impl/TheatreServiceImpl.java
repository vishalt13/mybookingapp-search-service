package com.vt.mba.search.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.vt.mba.search.entities.EventEntity;
import com.vt.mba.search.entities.TheatreEntity;
import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.repos.EventRepo;
import com.vt.mba.search.repos.TheatreRepo;
import com.vt.mba.search.services.TheatreService;
import com.vt.mba.search.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TheatreServiceImpl implements TheatreService {

	public static final String LOG_THEATRE_SERVICE = "[TheatreService] - {}";

	private final TheatreRepo theatreRepo;
//	private final ScreenRepo screenRepo;
	private final EventRepo eventRepo;

	/**
	 * Retrieve list of Theatre for given city, movie, date
	 */
	@Override
	public List<Theatre> getTheatreListResponse(String city, String movie, String date) {

		log.info(LOG_THEATRE_SERVICE, city + " " + movie + " " + date);
		List<TheatreEntity> theatreRecords = theatreRepo.findByLocationCityIgnoreCase(city);
		List<TheatreEntity> filteredTheatreList = new ArrayList<>();

		if (StringUtils.hasText(movie) || StringUtils.hasText(date)) {
			filteredTheatreList = getTheatresByMovieDateCity(city, movie, date);
		} else {
			log.info(LOG_THEATRE_SERVICE, "movie and date is empty");
			filteredTheatreList = theatreRecords;
		}

		return populateTheatreList(filteredTheatreList);
	}

	private List<Theatre> populateTheatreList(List<TheatreEntity> theatreRecords) {
		log.debug(LOG_THEATRE_SERVICE, "populate Theatre List");

		if (!CollectionUtils.isEmpty(theatreRecords)) {
			List<Theatre> theatreList = new ArrayList<>();

			theatreRecords.stream().forEach(rec -> {
				Theatre theatre = new Theatre();
				theatre.setTheatreId(rec.getTheatreId());
				theatre.setName(rec.getName());
				theatre.setAddress(rec.getAddress());
				if (Objects.nonNull(rec.getPartner())) {
					theatre.setPartnerName(rec.getPartner().getPartnerName());
				}

				if (Objects.nonNull(rec.getLocation())) {
					theatre.setCity(rec.getLocation().getCity());
					theatre.setState(rec.getLocation().getState());
					theatre.setCountry(rec.getLocation().getCountry());
				}

				theatreList.add(theatre);
			});
			return theatreList;
		}

		return new ArrayList<>();
	}

	private List<TheatreEntity> getTheatresByMovieDateCity(String city, String movie, String date) {

		List<TheatreEntity> filteredTheatreList = new ArrayList<>();
		List<EventEntity> events = new ArrayList<>();

		if (StringUtils.hasText(movie) && !StringUtils.hasText(date)) {
			events = eventRepo.findByMovieNameIgnoreCase(movie);
		} else if (!StringUtils.hasText(movie) && StringUtils.hasText(date)) {

			LocalDateTime dateTime = Utils.getLocalDateTime(date);
			events = eventRepo.findByStartTs(dateTime);

		} else if (StringUtils.hasText(movie) && StringUtils.hasText(date)) {
			LocalDateTime dateTime = Utils.getLocalDateTime(date);
			events = eventRepo.findByMovieNameIgnoreCaseAndStartTs(movie, dateTime);
		}

		if (!CollectionUtils.isEmpty(events)) {
			filteredTheatreList = events.stream().map(event -> event.getScreen().getTheatre()).distinct()
					.collect(Collectors.toList());
		}

		return filteredTheatreList;
	}

}
