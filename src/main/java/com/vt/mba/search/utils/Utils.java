package com.vt.mba.search.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

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
	 * Get LocalDateTime object for given date-time String
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime getLocalDateTime(String date) {
		if (StringUtils.hasText(date)) {
			if (date.length() == 10) {
				return LocalDateTime.of(LocalDate.parse(date), LocalTime.of(0, 0));
			}
			return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		}
		return LocalDateTime.now();
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