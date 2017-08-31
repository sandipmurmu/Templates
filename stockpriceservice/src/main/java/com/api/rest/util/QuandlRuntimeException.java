package com.api.rest.util;

public class QuandlRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3163723225666962347L;

	public QuandlRuntimeException(String m, Throwable e) {
		super(m,e);
	}
	
	public QuandlRuntimeException(String m) {
		super(m);
	}
}
