package com.ib.exception;

/**
 * Application exception is the business exception with related context information
 * 
 * @author ishmael
 *
 */
public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public ApplicationException(String errorMessage) {
		super(errorMessage);
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
