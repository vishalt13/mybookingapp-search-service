package com.vt.mba.search.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.vt.mba.search.exceptions.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestClient implements RetryHttpClient {

	private static final String LOG_REST_CLIENT = "[RestClient] - [{}] - ";
	private static final String LOG_REST_CLIENT_URL = "[RestClient] - [{}] - Request URL: {}";
	private static final String LOG_REST_CLIENT_HEADERS = "[RestClient] - [{}] - Request Headers: {}";
	private static final String LOG_REST_CLIENT_BODY = "[RestClient] - [{}] - Request Body: {}";
	private static final String LOG_REST_CLIENT_RESPONSE = "[RestClient] - [{}] - Response: {}";
	private static final String LOG_REST_CLIENT_ERROR = "[RestClient] - [{}] - Error Response: {}";

	private final RestTemplate searchRestTemplate;

	/**
	 * Generic method to call GET, POST, PUT, DELETE API with request body and
	 * headers
	 * 
	 * @param <T>
	 * @param url
	 * @param body
	 * @param headers
	 * @param httpMethod
	 * @param returnClass
	 * @return
	 */
	@Override
	public <X, Y> ResponseEntity<Y> exchange(String url, X body, HttpHeaders headers, HttpMethod httpMethod,
			Class<Y> returnClass) {
		log.info(LOG_REST_CLIENT_URL, httpMethod, url);
		log.debug(LOG_REST_CLIENT_HEADERS, httpMethod, Utils.convertToJsonString(headers));
		log.debug(LOG_REST_CLIENT_BODY, httpMethod, Utils.convertToJsonString(body));

		ResponseEntity<Y> response = null;
		HttpEntity<X> requestEntity = new HttpEntity<>(body, headers);
		try {
			response = searchRestTemplate.exchange(url, httpMethod, requestEntity, returnClass);
			log.debug(LOG_REST_CLIENT_RESPONSE, httpMethod, Utils.convertToJsonString(response));
			return response;

		} catch (HttpStatusCodeException error) {
			log.error(LOG_REST_CLIENT_URL, httpMethod, url);
			log.error(LOG_REST_CLIENT_HEADERS, httpMethod, Utils.convertToJsonString(requestEntity.getHeaders()));
			log.error(LOG_REST_CLIENT_BODY, httpMethod, Utils.convertToJsonString(requestEntity.getBody()));
			log.error(LOG_REST_CLIENT_ERROR, error.getStatusCode(), error.toString());
			throw error;
		}
	}

	@Override
	public ResponseEntity<String> fallbackResponse(ResourceAccessException ex, String url) {
		log.error(LOG_REST_CLIENT, ex.toString());
		log.warn("[RestClient] - [Fallback Response for [{}]", url);
		throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage());
	}

	@Override
	public ResponseEntity<String> fallbackResponse(HttpServerErrorException ex, String url) {
		log.error(LOG_REST_CLIENT, ex.toString());
		log.warn("[RestClient] - [Fallback Response for [{}]", url);
		throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage());
	}

	@Override
	public ResponseEntity<String> defaultFallbackResponse(RuntimeException ex, String url) {
		log.error(LOG_REST_CLIENT, ex.toString());
		throw ex;
	}

}
