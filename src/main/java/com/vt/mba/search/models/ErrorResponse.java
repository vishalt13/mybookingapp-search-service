package com.vt.mba.search.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	@JsonProperty("error_code")
	private String errorCode;

	@JsonProperty("error_message")
	private String errorMessage;

}
