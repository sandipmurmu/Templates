package com.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiService {

	private static final Logger logger = LoggerFactory.getLogger(RestApiService.class);
	public static void main(String[] args) {
		SpringApplication.run(RestApiService.class, args);
		logger.info("starting REST Api service");
	}
}
