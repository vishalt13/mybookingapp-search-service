package com.vt.mba.search.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.mba.search.entities.TheatreEntity;

public interface TheatreRepo extends JpaRepository<TheatreEntity, Long> {

	List<TheatreEntity> findByLocationCityIgnoreCase(String city);

}
