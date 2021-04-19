package com.melichallenge.mutant.exception;

public abstract class BaseGeneralException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public BaseGeneralException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}