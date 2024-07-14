package com.vt.mba.search.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Offer")
public class OfferEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private Integer offerId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "tnc")
	private String tnc;

	@Column(name = "available_from")
	private LocalDateTime availableFrom;

	@Column(name = "expires_on")
	private LocalDateTime expiresOn;

	@Column(name = "discount")
	private Double discount;

	@ManyToMany(mappedBy = "offers")
	private Set<TheatreEntity> theatres;

}
