package com.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.api.rest.webservice.StockPriceWebService;

/**
 * BootStrap component for Stock Price application
 * @author Sandip Murmu
 *
 */
@SpringBootApplication
@EnableCaching
public class StockPriceApp {

	private static final Logger logger = LoggerFactory.getLogger(StockPriceApp.class);
	public static void main(String[] args) {
		SpringApplication.run(StockPriceApp.class, args);
		logger.info("starting stock price service");
	}
}
