package com.vt.mba.search.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.mba.search.entities.ScreenEntity;

public interface ScreenRepo extends JpaRepository<ScreenEntity, Integer> {

	List<ScreenEntity> findByTheatreTheatreIdIn(List<Integer> theatreIds);

}
