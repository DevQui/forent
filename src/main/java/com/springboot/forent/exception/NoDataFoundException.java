package com.springboot.forent.exception;

@SuppressWarnings("serial")
public class NoDataFoundException extends RuntimeException {

	public NoDataFoundException() {
			super("No data found");
	}
}
