package com.ib.exception;

public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public ApplicationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	public ApplicationException(String errorMessage, Object ... params) {
		super();
		this.errorMessage = String.format(errorMessage, params);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
