package com.vt.mba.search.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.mba.search.entities.TheatreEntity;

public interface TheatreRepo extends JpaRepository<TheatreEntity, Long> {

}
