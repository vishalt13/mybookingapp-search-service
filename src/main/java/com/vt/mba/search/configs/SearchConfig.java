package com.vt.mba.search.configs;

import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SearchConfig {

	public static final String LOG_SEARCH_CONFIG = "[SEARCH_CONFIG] - {}";
	private final AppConfig appConfig;

	@Bean
	public RestTemplate searchRestTemplate() {
		log.info(LOG_SEARCH_CONFIG, "Search restTemplate");
		RestTemplate restTemplate = new RestTemplate(bufferingClientHttpRequestFactory());
//		restTemplate .setRequestFactory(bufferingClientHttpRequestFactory());
		return restTemplate;
	}

	@Primary
	@Bean("searchHttpRequestFactory")
	public ClientHttpRequestFactory bufferingClientHttpRequestFactory() {
		log.debug(LOG_SEARCH_CONFIG, "Buffering Client Http Request Factory");
		try (PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager()) {
			connectionManager.setMaxTotal(appConfig.getConnectionMaxTotal());
			connectionManager.setDefaultMaxPerRoute(appConfig.getConnectionDefaultMax());
		}
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(appConfig.getConnectionTimeout() * 1000);
		httpRequestFactory.setConnectTimeout(appConfig.getReadTimeout() * 1000);

		return new BufferingClientHttpRequestFactory(httpRequestFactory);
	}

	@Primary
	@Bean("asyncTaskExecutor")
	public TaskExecutor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(appConfig.getAsyncThreadCorePoolSize());
		executor.setMaxPoolSize(appConfig.getAsyncThreadMaxPoolSize());
		executor.setQueueCapacity(appConfig.getAsyncThreadQueueCapacity());
		executor.setThreadNamePrefix(appConfig.getAsyncThreadNamePrefix());
		executor.initialize();
		return executor;
	}

}
