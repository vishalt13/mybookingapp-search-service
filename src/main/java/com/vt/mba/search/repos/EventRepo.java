package com.vt.mba.search.repos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.mba.search.entities.EventEntity;

public interface EventRepo extends JpaRepository<EventEntity, Integer> {

	List<EventEntity> findByMovieNameIgnoreCase(String movie);

	List<EventEntity> findByStartTs(LocalDateTime dateTime);

	List<EventEntity> findByMovieNameIgnoreCaseAndStartTs(String movie, LocalDateTime dateTime);

}
