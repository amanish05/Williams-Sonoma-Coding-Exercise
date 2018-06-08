package com.code.model;

public class ZipRange {
	
	private Integer upperBond;
	private Integer lowerBond;
	
	public ZipRange(int i, int j) {
		this.setLowerBond(i);
		this.setUpperBond(j);
	}
	
	public ZipRange() {		
	}
	
	public Integer getUpperBond() {
		return upperBond;
	}
	public void setUpperBond(Integer upperBond) {
		this.upperBond = upperBond;
	}
	public Integer getLowerBond() {
		return lowerBond;
	}
	public void setLowerBond(Integer lowerBond) {
		this.lowerBond = lowerBond;
	}
	
	@Override
	public String toString() {		
		return "[" + this.getLowerBond() + ", " +this.getUpperBond() + "]";
	}
	
	public static boolean validateZipRange(ZipRange zip) {		
		if(zip.getLowerBond() <= zip.getUpperBond())
			return true;
		else
			throw new IllegalArgumentException("Lower Range should be less than Upper range");
	}

}
