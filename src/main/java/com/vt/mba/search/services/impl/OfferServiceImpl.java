package com.vt.mba.search.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vt.mba.search.entities.OfferEntity;
import com.vt.mba.search.entities.TheatreEntity;
import com.vt.mba.search.models.Offer;
import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.repos.OfferRepo;
import com.vt.mba.search.repos.TheatreRepo;
import com.vt.mba.search.services.OfferService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

	public static final String LOG_OFFER_SERVICE = "[OfferService] - {}";

	private final TheatreRepo theatreRepo;
	private final OfferRepo offerRepo;

	@Override
	public List<Theatre> getTheatreListWithOffer(String offerId) {

		log.debug(LOG_OFFER_SERVICE, "Get Theatre List With Offer " + offerId);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getOffersForCityTheatre(String city, String theatreId) {
		log.info(LOG_OFFER_SERVICE, "Get Offers For City & Theatre");
		List<TheatreEntity> theatreRecords = theatreRepo.findByLocationCityIgnoreCase(city);
		List<Offer> offerList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(theatreRecords)) {

			List<OfferEntity> offerRecords = offerRepo.findByTheatres(new HashSet<>(theatreRecords));
			if (!CollectionUtils.isEmpty(offerRecords)) {
				offerRecords.forEach(rec -> {
					Offer offer = Offer.builder().offerId(rec.getOfferId()).name(rec.getName())
							.termsAndConditions(rec.getTnc()).description(rec.getDescription())
							.availablefrom(rec.getAvailableFrom()).expiresOn(rec.getExpiresOn())
							.discount(rec.getDiscount()).build();
					offerList.add(offer);
				});
			}
		}

		return offerList;
	}

}
