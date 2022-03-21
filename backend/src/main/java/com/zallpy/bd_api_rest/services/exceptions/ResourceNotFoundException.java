package com.zallpy.bd_api_rest.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Cadastro não encontrado. Código " + id + " inválido.");
	}
}
