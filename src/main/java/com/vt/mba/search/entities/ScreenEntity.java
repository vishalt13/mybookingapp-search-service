package com.vt.mba.search.entities;

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
@Table(name = "Screen")
public class ScreenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screen_id")
	private Integer screenId;

	@Column(name = "allocated_capacity")
	private Integer allocatedCapacity;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "theatre_id")
	private TheatreEntity theatre;

	@OneToMany(mappedBy = "screen")
	private Set<EventEntity> events;
}
