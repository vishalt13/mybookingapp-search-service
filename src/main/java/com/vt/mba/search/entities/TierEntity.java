package com.vt.mba.search.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Tier")
public class TierEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tier_id")
	private Integer tierId;

	@Column(name = "type")
	private String type;

	@Column(name = "price")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity event;
}
