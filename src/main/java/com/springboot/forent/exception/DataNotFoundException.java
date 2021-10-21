package com.springboot.forent.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException{
	
	public DataNotFoundException(Integer id) {
		super(String.format("Data with the ID %d is not found", id));
	}
}
