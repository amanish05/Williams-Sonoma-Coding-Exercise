package com.code.challenge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.code.model.ZipRange;
import com.code.services.RangeServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsCodingExerciseApplicationTests {
	
	private List<ZipRange> actualRangesWithoutOverlap = new ArrayList<>(3);
	private List<ZipRange> actualRangesWithOverlap = new ArrayList<>(3);
	private RangeServices services = null;

	@Before
	public void contextLoads() {
		ZipRange z1 = new ZipRange(94133,94133);
		ZipRange z2 = new ZipRange(94200,94299);
		ZipRange z3 = new ZipRange(94600,94699);		
		actualRangesWithoutOverlap.add(z1);
		actualRangesWithoutOverlap.add(z2);
		actualRangesWithoutOverlap.add(z3);
		
		ZipRange z4 = new ZipRange(94133,94133);
		ZipRange z5 = new ZipRange(94200,94299);
		ZipRange z6 = new ZipRange(94226,94399);
		actualRangesWithOverlap.add(z4);
		actualRangesWithOverlap.add(z5);
		actualRangesWithOverlap.add(z6);
		
		services = new RangeServices();
	}
	
	@Test
	public void testWithoutRangeOverlap() {
		
		List<ZipRange> expectedRanges = new ArrayList<>(3);
		ZipRange e1 = new ZipRange(94133,94133);		
		ZipRange e2 = new ZipRange(94200,94299);		
		ZipRange e3 = new ZipRange(94600,94699);
		
		expectedRanges.add(e1);
		expectedRanges.add(e2);
		expectedRanges.add(e3);	
		
		assertEquals(expectedRanges.size(), services.getNormalizedRange(actualRangesWithoutOverlap).size());		
	}
	
	@Test
	public void testWithRangeOverlap() {
		
		List<ZipRange> expectedRanges = new ArrayList<>(2);
		ZipRange e1 = new ZipRange(94133,94133);		
		ZipRange e2 = new ZipRange(94200,94399);
		
		expectedRanges.add(e1);
		expectedRanges.add(e2);		
		
		assertEquals(expectedRanges.size(), services.getNormalizedRange(actualRangesWithOverlap).size());
	}
	
	@Test
	public void testIllegalArgumentException() {
		
		List<ZipRange> expectedRanges = new ArrayList<>(3);
		ZipRange e1 = new ZipRange(92004,92002);		
		ZipRange e2 = new ZipRange(92003,92004);
		
		expectedRanges.add(e1);
		expectedRanges.add(e2);
	    try {
	    	services.getNormalizedRange(actualRangesWithOverlap);
	    } catch (IllegalArgumentException e) {
	      assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
	    }
	  }

}
