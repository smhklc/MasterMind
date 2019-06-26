package com.project.dto;

public class GuessResult {

	private int errorCode;
	private String breakerGuess;
	
	public GuessResult(int errorCode, String breakerGuess) {
		super();
		this.errorCode = errorCode;
		this.breakerGuess = breakerGuess;
	}
	
	public String getBreakerGuess() {
		return breakerGuess;
	}
	public void setBreakerGuess(String breakerGuess) {
		this.breakerGuess = breakerGuess;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
