package com.vt.mba.search.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	private int connectionTimeout;
	private int readTimeout;
	private int retryBackoffPeriod;
	private int retryMaxAttempts;

	private int connectionMaxTotal;
	private int connectionDefaultMax;

	private int asyncThreadCorePoolSize;
	private int asyncThreadMaxPoolSize;
	private int asyncThreadQueueCapacity;
	private String asyncThreadNamePrefix;

}
