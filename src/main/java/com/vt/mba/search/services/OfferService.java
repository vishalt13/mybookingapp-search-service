package com.vt.mba.search.services;

import java.util.List;

import com.vt.mba.search.models.Offer;
import com.vt.mba.search.models.Theatre;

public interface OfferService {

	List<Theatre> getTheatreListWithOffer(String offerId);

	List<Offer> getOffersForCityTheatre(String city, String theatreId);

}
