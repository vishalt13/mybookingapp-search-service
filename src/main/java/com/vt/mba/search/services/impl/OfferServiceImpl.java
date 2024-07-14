package com.vt.mba.search.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vt.mba.search.entities.OfferEntity;
import com.vt.mba.search.entities.TheatreEntity;
import com.vt.mba.search.models.Offer;
import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.repos.OfferRepo;
import com.vt.mba.search.repos.TheatreRepo;
import com.vt.mba.search.services.OfferService;
import com.vt.mba.search.utils.CommonUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

	public static final String LOG_OFFER_SERVICE = "[OfferService] - {}";

	private final TheatreRepo theatreRepo;
	private final OfferRepo offerRepo;
	private final CommonUtils commonUtils;

	@Override
	public List<Theatre> getTheatreListWithOffer(Integer offerId) {
		log.debug(LOG_OFFER_SERVICE, "Get Theatre List With Offer " + offerId);

		Optional<OfferEntity> offerRecordOpt = offerRepo.findById(offerId);

		if (offerRecordOpt.isPresent()) {
			List<TheatreEntity> theatreRecords = theatreRepo.findByOffers(List.of(offerRecordOpt.get()));
			return commonUtils.populateTheatreList(theatreRecords);
		}
		log.warn(LOG_OFFER_SERVICE, "Theatre records not found");
		return new ArrayList<>();
	}

	@Override
	public List<Offer> getOffersForCityTheatre(String city, Integer theatreId) {
		log.info(LOG_OFFER_SERVICE, "Get Offers For City & Theatre");

		List<TheatreEntity> theatreRecords = new ArrayList<>();
		theatreRecords = theatreRepo.findByLocationCityIgnoreCase(city);

		List<Offer> offerList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(theatreRecords)) {
			if (Objects.nonNull(theatreId)) {
				log.info(LOG_OFFER_SERVICE, "Check for matching theatreId : " + theatreId);
				theatreRecords = theatreRecords.stream().filter(theatre -> theatreId.equals(theatre.getTheatreId()))
						.collect(Collectors.toList());
			}

			List<OfferEntity> offerRecords = offerRepo.findByTheatresIn(theatreRecords);
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
