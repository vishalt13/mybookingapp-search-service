package com.vt.mba.search.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Partner")
public class PartnerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partner_id")
	private Integer partnerId;

	@Column(name = "partner_name")
	private String partnerName;

	@OneToMany(mappedBy = "partner")
	private Set<TheatreEntity> theatres;

}
