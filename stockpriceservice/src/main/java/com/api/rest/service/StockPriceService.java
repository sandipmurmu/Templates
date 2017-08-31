package com.api.rest.service;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.rest.data.ClosePrice;
import com.api.rest.data.MovingAverage;
import com.api.rest.data.Ticker;
import com.api.rest.exception.SentifiException;
import com.api.rest.quandl.QuandlDataService;
import com.api.rest.quandl.QuandlRequest;
import com.api.rest.quandl.MovingAverageTask;
import com.api.rest.quandl.TableRequest;
import com.api.rest.quandl.TimeSeriesRequest;
import com.api.rest.util.DateUtil;
import com.api.rest.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;



@Service
public class StockPriceService {

	
	
	@Value("${auth}")
	private String authToken;
	
	@Cacheable(value="top-tickers", key="#ticker.ticker", unless="#ticker.reqCount < 10000")
	public Map<String,Object> getPrice(Ticker ticker, String startDate, String endDate, String [] columns) {
		Map<String,Object> result = new HashMap<>();;
		
			QuandlDataService quandl = QuandlDataService.createSession(authToken);
			String cols = String.join(",", columns);
			QuandlRequest request = TableRequest.forTicker(ticker.getTicker())
										.withColumns(cols)
										.withStartDate(startDate)
										.withEndDate(endDate);
		    
			String json= quandl.getData(request);
			JsonNode data = JsonUtil.getData(json, "data");
			
			
			ClosePrice closePrice = new ClosePrice();
			closePrice.setData(data);
			closePrice.setTicker(ticker.getTicker());
			
			result.put("Prices", closePrice);
	       
		return result;

	}
	
	public Map<String,Object> getMovingAverage(List<String> tickers, Long days, String startDate,String jsonRoot) {
		Map<String,Object> result = new HashMap<>();
		//String ticker = tickers.stream().map(String::toUpperCase).collect(Collectors.joining(","));
		String endDate = DateUtil.addDays(days, startDate);
		List<MovingAverageTask> executables = new ArrayList<>();
			QuandlRequest req = null;
			for(String ticker : tickers) {
				
				req = TimeSeriesRequest.forTicker(ticker)
						.withStartDate(startDate)
						.withEndDate(endDate)
						.withColumnIndex("4")
						.withTransform("cumul")
						.withLimit("1");
				MovingAverageTask task = new MovingAverageTask(req, authToken, ticker, days);
				executables.add(task);
			}
		
		//using executor for CPU intensive processing
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
		
		List<CompletableFuture<MovingAverage>> futures = executables
				.stream()
				.map(task -> CompletableFuture.supplyAsync(() -> task.execute(),executor))
				.collect(Collectors.toList());
		
		List<MovingAverage> res = futures
				.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
		executor.shutdown();
		
		result.put(jsonRoot, res);
		return result;
	}
	
	
	
	
}
