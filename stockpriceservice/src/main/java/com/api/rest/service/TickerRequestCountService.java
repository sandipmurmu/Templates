package com.api.rest.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TickerRequestCountService {

	private static final Logger logger = LoggerFactory.getLogger(TickerRequestCountService.class);
	private ConcurrentMap<String, Integer> countMetric =  new ConcurrentHashMap();
	
     
    public void increaseCount(String ticker, int count) {
        Integer countStatus = countMetric.get(ticker);
        if (null == countStatus) {
        	countMetric.put(ticker, 1);
        } else {
        	countMetric.put(ticker, countStatus + 1);
        }
        logger.info("Ticker " + ticker + "  requested "+ countMetric.get(ticker) + " time");
    }
 
    public Map<String,Integer> getStatusMetric() {
        return countMetric;
    }
}
