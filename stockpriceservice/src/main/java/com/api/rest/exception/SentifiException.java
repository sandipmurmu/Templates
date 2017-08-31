package com.api.rest.exception;

public class SentifiException extends RuntimeException{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public SentifiException(String message, Throwable e) {
			super(message, e);
		}
	
		public SentifiException(String message) {
			super(message);
		}
}
