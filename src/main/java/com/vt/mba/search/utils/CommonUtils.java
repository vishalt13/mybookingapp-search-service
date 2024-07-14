package com.vt.mba.search.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vt.mba.search.entities.TheatreEntity;
import com.vt.mba.search.models.Theatre;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtils {

	private static final String LOG_COMMON_UTILS = "[CommonUtils] - [{}] - ";

	/**
	 * Populate Theatre List
	 * 
	 * @param theatreRecords
	 * @return
	 */
	public List<Theatre> populateTheatreList(List<TheatreEntity> theatreRecords) {
		log.debug(LOG_COMMON_UTILS, "populate Theatre List");

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
		log.warn(LOG_COMMON_UTILS, "Theatre record list is empty");
		return new ArrayList<>();
	}
}
