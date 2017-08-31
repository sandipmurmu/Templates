package com.api.rest.webservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.data.Ticker;
import com.api.rest.service.StockPriceService;
import com.api.rest.service.TickerRequestCountService;


@RequestMapping("/api")
@RestController
public class StockPriceWebService {
	
	@Autowired
	private TickerRequestCountService tickerRequest;
	
	@Autowired
	public StockPriceService stocks;
	
	@RequestMapping(method=RequestMethod.GET, value="/v2/test")
	public String test() {
		
		return "Hello Sentifi";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/v2/{tickersymbol}/closePrice")
	public ResponseEntity<Map<String,Object>> closePrice(
			@PathVariable("tickersymbol") String tickersymbol,
			@RequestParam("startDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
		    
			
			tickerRequest.increaseCount(tickersymbol, 1);
			int reqCount = tickerRequest.getStatusMetric().get(tickersymbol);
			Ticker ticker = new Ticker();
			ticker.setTicker(tickersymbol);
			ticker.setReqCount(reqCount);
			
			String columns[] = {"date,close"};
		    Map<String,Object> result = stocks.getPrice(ticker, startDate.toString(), endDate.toString(),columns);
		 
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/v2/{tickersymbol}/200dma")
	public ResponseEntity<Map<String,Object>> movingAverage(
			@PathVariable("tickersymbol") List<String> tickers,
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate){
		Map<String,Object> result = stocks.getMovingAverage(tickers, 200L, startDate.toString(),"200dma");
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	
	
	
}
