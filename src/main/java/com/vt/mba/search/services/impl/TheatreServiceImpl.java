package com.vt.mba.search.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vt.mba.search.entities.TheatreEntity;
import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.repos.TheatreRepo;
import com.vt.mba.search.services.TheatreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TheatreServiceImpl implements TheatreService {

	public static final String LOG_THEATRE_SERVICE = "[TheatreService] - {}";

	private final TheatreRepo theatreRepo;

	/**
	 * Retrieve list of Theatre for given city, movie, date
	 */
	@Override
	public List<Theatre> getTheatreListResponse(String city, String movie, String date) {

		log.info(LOG_THEATRE_SERVICE, city + " " + movie + " " + date);
		List<TheatreEntity> theatreRecords = theatreRepo.findAll();
		return populateTheatreList(theatreRecords);
	}

	private List<Theatre> populateTheatreList(List<TheatreEntity> theatreRecords) {
		log.debug(LOG_THEATRE_SERVICE, "populate Theatre List");
		List<Theatre> theatreList = new ArrayList<>();
		BeanUtils.copyProperties(theatreRecords, theatreList);
		return theatreList;
	}

}
