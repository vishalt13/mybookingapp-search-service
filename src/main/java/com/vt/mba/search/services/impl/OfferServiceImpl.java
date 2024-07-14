package com.vt.mba.search.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vt.mba.search.models.Offer;
import com.vt.mba.search.models.Theatre;
import com.vt.mba.search.services.OfferService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

	public static final String LOG_OFFER_SERVICE = "[OfferService] - {}";

	@Override
	public List<Theatre> getTheatreListWithOffer(String offerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getOffersForCityTheatre(String city, String theatreId) {
		// TODO Auto-generated method stub
		return null;
	}

}
