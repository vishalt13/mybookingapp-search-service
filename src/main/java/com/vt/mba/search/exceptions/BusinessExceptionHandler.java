package com.vt.mba.search.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vt.mba.search.models.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessExceptionss(BusinessException ex) {
		log.error("Handle Bussiness Exceptions");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ex.getErrorCode());
		errorResponse.setErrorMessage(ex.getErrorMsg());

		return new ResponseEntity<ErrorResponse>(errorResponse, ex.getHttpStatus());

	}
}
