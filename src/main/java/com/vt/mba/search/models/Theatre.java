package com.vt.mba.search.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Theatre {

	@JsonProperty("theatreId")
	private String theatreId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("partnerName")
	private String partnerName;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("country")
	private String country;

}
