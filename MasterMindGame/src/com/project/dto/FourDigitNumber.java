package com.project.dto;

public class FourDigitNumber {
	private int fullNumber;
	private int digits[];
	
	public FourDigitNumber() {
		// TODO Auto-generated constructor stub
	}
	
	public FourDigitNumber(int fullNumber, int[] digits) {
		super();
		this.fullNumber = fullNumber;
		this.digits = digits;
	}
	
	public int getFullNumber() {
		return fullNumber;
	}
	public void setFullNumber(int fullNumber) {
		this.fullNumber = fullNumber;
	}
	public int[] getDigits() {
		return digits;
	}
	public void setDigits(int[] digits) {
		this.digits = digits;
	}
	
}
