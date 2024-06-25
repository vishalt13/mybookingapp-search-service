package com.vt.mba.search.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String errorMsg;

	public BusinessException(HttpStatus httpStatus, String errorCode, String errorMsg) {
		super(String.valueOf(httpStatus.value()).concat(" ").concat(httpStatus.name()).concat(" ").concat(errorMsg));
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

}
