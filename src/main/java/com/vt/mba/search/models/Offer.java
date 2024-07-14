package com.vt.mba.search.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Offer {

	@JsonProperty("offerId")
	private String offerId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("termsAndConditions")
	private String termsAndConditions;
	
	@JsonProperty("availablefrom")
	private LocalDateTime availablefrom;
	
	@JsonProperty("expiresOn")
	private LocalDateTime expiresOn;
	

}
