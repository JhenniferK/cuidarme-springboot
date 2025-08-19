package br.edu.ifpb.es.cuidarme.exception;

public class SistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	public SistemaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SistemaException(String message) {
		super(message);
	}

}
