package com.jaxRs.PocProject.Exception;

public class ErrorMessage {
	
	private String errorMessage;
	private Integer errorCode;
	
	
	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String errorMessage, Integer errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
