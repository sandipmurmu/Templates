package com.api.rest.webservice;

import java.util.concurrent.CompletionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.rest.exception.SentifiException;
import com.api.rest.quandl.QuandlRuntimeException;

@ControllerAdvice
public class SentifiExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(SentifiExceptionHandler.class);
	
	@ExceptionHandler(SentifiException.class)
	public ResponseEntity<String> handleSentifiException(SentifiException e) {
		logger.info("SentifiException occured",e);
		String msg = e.getMessage();
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ExceptionHandler(QuandlRuntimeException.class)
	public ResponseEntity<String> handleQuandlRuntimeException(QuandlRuntimeException e) {
		logger.info("QuandlRuntimeException occured",e);
		String msg = e.getMessage();
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception e) {
		logger.info("Generic Exception occured",e);
		String msg = getRootCause(e).getMessage();		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@ExceptionHandler(CompletionException.class)
	public ResponseEntity<String> handleCompletionException(CompletionException e) {
		logger.info("CompletionException occured",e);
		
		String msg = getRootCause(e).getMessage();		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	private Throwable getRootCause(Throwable e) {
		Throwable cause = null;
		Throwable result= e;
		while(null != (cause = result.getCause())  && (result != cause) ) {
	        result = cause;
	    }
	    return result;
		
	}
}
