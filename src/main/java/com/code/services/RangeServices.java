package com.code.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.code.model.ZipRange;

public class RangeServices {	
	
	public List<ZipRange> getNormalizedRange(List<ZipRange> rangesToNormalize) {
		
		if(rangesToNormalize == null)
			return new ArrayList<>();
		
		if(rangesToNormalize.size() == 1)
			return rangesToNormalize;
		
		Set<ZipRange> uniqueZipSet = new TreeSet<>(Comparator.comparingInt(ZipRange::getLowerBond));
		uniqueZipSet.addAll(rangesToNormalize);
		
		return mergeZipcodes(uniqueZipSet.stream().filter(range -> uncheckCall(range)).collect(Collectors.toList()));
	}
	
	public static boolean uncheckCall(ZipRange range) {
		try {
			 return ZipRange.validateZipRange(range);
		}
		catch (RuntimeException e) { throw e; }
		catch (Exception e) { throw new RuntimeException(e); }	  
	}
	
	public List<ZipRange> mergeZipcodes(List<ZipRange> sortedZipCodeList) {

		List<ZipRange> mergedZipcodeList = new ArrayList<ZipRange>();
		ZipRange zipcode = null;

		for (ZipRange zipcodeInterval : sortedZipCodeList) {
			if (zipcode == null)
				zipcode = zipcodeInterval;
			else {
				if (zipcode.getUpperBond() >= zipcodeInterval.getLowerBond()) {
					zipcode.setUpperBond(Math.max(zipcode.getUpperBond(), zipcodeInterval.getUpperBond()));
				} else {
					mergedZipcodeList.add(zipcode);
					zipcode = zipcodeInterval;
				}
			}
		}
		mergedZipcodeList.add(zipcode);
		return mergedZipcodeList;
	}	
	
}
