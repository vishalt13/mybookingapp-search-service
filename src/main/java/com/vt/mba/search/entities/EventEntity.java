package com.vt.mba.search.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Event")
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Integer eventId;

	@JoinColumn(name = "partner_event_id")
	private String partnerEventId;

	@JoinColumn(name = "start_ts")
	private LocalDateTime startTs;

	@JoinColumn(name = "movie_name")
	private String movieName;

	@JoinColumn(name = "language")
	private String language;

	@JoinColumn(name = "genre")
	private String genre;

	@JoinColumn(name = "duration")
	private Integer duration;

	@JoinColumn(name = "available_seat_count")
	private Integer availableSeatCount;

	@ManyToOne
	@JoinColumn(name = "screen_id")
	private ScreenEntity screen;

	@OneToMany(mappedBy = "event")
	private Set<TierEntity> tier;

}
