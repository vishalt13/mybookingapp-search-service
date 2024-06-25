package com.vt.mba.search.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

public interface RetryHttpClient {

	@Retryable(retryFor = { HttpServerErrorException.class,	ResourceAccessException.class },
			maxAttemptsExpression = "${app.retry-max-attempts}", backoff = @Backoff(delayExpression = "${app.retry-backoff-period}"))
	public <X, Y> ResponseEntity<Y> exchange(String url, X body, HttpHeaders headers, HttpMethod httpMethod,
			Class<Y> returnClass);

	@Recover
	public ResponseEntity<String> fallbackResponse(ResourceAccessException ex, String url);

	@Recover
	public ResponseEntity<String> fallbackResponse(HttpServerErrorException ex, String url);

	@Recover
	public ResponseEntity<String> defaultFallbackResponse(RuntimeException ex, String url);

}
