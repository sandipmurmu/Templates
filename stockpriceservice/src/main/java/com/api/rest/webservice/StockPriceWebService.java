package com.api.rest.webservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.service.StockPriceService;


@RequestMapping("/api")
@RestController
public class StockPriceWebService {
	
	@Autowired
	public StockPriceService stocks;
	
	@RequestMapping(method=RequestMethod.GET, value="/v2/test")
	public String test() {
		
		return "Hello Sentifi";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/v2/{tickersymbol}/closePrice")
	public ResponseEntity<Map<String,Object>> closePrice(
			@PathVariable("tickersymbol") String ticker,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate){
		    
			String columns[] = {"date,close"};
		   
		    Map<String,Object> result = stocks.getPrice(ticker, startDate, endDate,columns);
		 
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/v2/{tickersymbol}/200dma")
	public ResponseEntity<Map<String,Object>> movingAverage(
			@PathVariable("tickersymbol") List<String> tickers,
			@RequestParam("startDate") String startDate){
		Map<String,Object> result = stocks.getMovingAverage(tickers, 200L, startDate,"200dma");
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	
	
	
}
