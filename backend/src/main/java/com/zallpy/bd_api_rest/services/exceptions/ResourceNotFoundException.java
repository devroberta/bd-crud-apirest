package com.zallpy.bd_api_rest.services.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public ResourceNotFoundException(Object id) {
		super("ERRO: "+id);
	}
	
	public ResourceNotFoundException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
