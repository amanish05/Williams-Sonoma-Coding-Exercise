package com.code.utils;

import java.util.ArrayList;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;

import com.code.model.ZipRange;

public class Utils {

	public static List<ZipRange> generateRandomZipcodeData(int noOfDataSets) {
		DataFactory dataFactory = new DataFactory();
		List<ZipRange> dataSet = new ArrayList<>(noOfDataSets);
		for (int i = 0; i < noOfDataSets; i++) {
			ZipRange zip = new ZipRange();
			Integer lower_bound = dataFactory.getNumberBetween(10000, 99999);
			Integer upper_bound = dataFactory.getNumberBetween(lower_bound, 99999);

			zip.setLowerBond(lower_bound);
			zip.setUpperBond(upper_bound);
			dataSet.add(zip);
		}
		return dataSet;
	}
}
