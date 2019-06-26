package com.project.dto;

public class Hint {
	private int plusCount;
	private int minusCount;

	public Hint(int plusCount, int minusCount) {
		super();
		this.plusCount = plusCount;
		this.minusCount = minusCount;
	}
	
	public boolean equals(Hint hint) {
		if (this.getHintString().contentEquals(hint.getHintString())) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getHintString() {
		return "+" + plusCount + " -" + minusCount;		
	}

	public int getPlusCount() {
		return plusCount;
	}

	public void setPlusCount(int plusCount) {
		this.plusCount = plusCount;
	}

	public int getMinusCount() {
		return minusCount;
	}

	public void setMinusCount(int minusCount) {
		this.minusCount = minusCount;
	}

}
