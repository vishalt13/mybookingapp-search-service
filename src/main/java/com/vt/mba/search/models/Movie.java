package com.vt.mba.search.models;

import java.time.LocalDate;
import java.util.List;

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
public class Movie {

	@JsonProperty("movieId")
	private String movieId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("orginalLanguage")
	private String orginalLanguage;
	
	@JsonProperty("dubbedLanguage")
	private List<String> dubbedLanguages;
	
	@JsonProperty("cast")
	private List<String> cast;
	
	@JsonProperty("releasedOn")
	private LocalDate releasedOn;
	
	@JsonProperty("originCountry")
	private String originCountry;
	
	@JsonProperty("genre")
	private List<String> genre;

}
