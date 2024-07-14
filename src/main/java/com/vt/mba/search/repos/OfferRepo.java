package com.vt.mba.search.repos;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.mba.search.entities.OfferEntity;
import com.vt.mba.search.entities.TheatreEntity;

public interface OfferRepo extends JpaRepository<OfferEntity, Integer> {

	List<OfferEntity> findByTheatres(HashSet<TheatreEntity> hashSet);

}
