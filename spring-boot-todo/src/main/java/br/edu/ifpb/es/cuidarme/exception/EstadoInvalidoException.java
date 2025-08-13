package br.edu.ifpb.es.cuidarme.exception;

import lombok.Getter;

@Getter
public class EstadoInvalidoException extends SistemaException {

	private static final long serialVersionUID = 1L;

	public EstadoInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EstadoInvalidoException(String message) {
		super(message);
	}
	
}