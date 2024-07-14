package com.vt.mba.search.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Theatre")
public class TheatreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theatre_id")
	private Integer theatreId;

	@Column(name = "partner_theatre_id")
	private String partnerTheatreId;

	@Column(name = "name")
	private String name;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "partner_id")
	private PartnerEntity partner;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private LocationEntity location;

	@OneToMany(mappedBy = "theatre", fetch = FetchType.EAGER)
	private Set<ScreenEntity> screens;

	@ManyToMany
	@JoinTable(name = "map_theatre_offer", joinColumns = @JoinColumn(name = "theatre_id"), inverseJoinColumns = @JoinColumn(name = "offer_id"))
	private Set<OfferEntity> offers;

}
