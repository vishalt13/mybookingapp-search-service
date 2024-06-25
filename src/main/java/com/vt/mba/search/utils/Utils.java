package com.vt.mba.search.utils;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vt.mba.search.exceptions.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {

	private Utils() {
	}

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		// NON_EMPTY for '' or NULL value
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	/**
	 * convert object to json string
	 */
	public static String convertToJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException ex) {
			log.error("failure to convert object to json", ex);
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage());
		}
	}
}