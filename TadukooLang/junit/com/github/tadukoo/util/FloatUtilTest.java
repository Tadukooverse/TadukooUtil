package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatUtilTest{
	
	@Test
	public void testConvertListToArray(){
		// Setup the floats in a List
		float float1 = 4.59f;
		float float2 = 395.4f;
		float float3 = 1.2f;
		List<Float> theFloats = new ArrayList<>();
		theFloats.add(float1);
		theFloats.add(float2);
		theFloats.add(float3);
		
		// Convert to array and verify
		float[] theArray = FloatUtil.convertListToArray(theFloats);
		assertEquals(3, theArray.length);
		assertEquals(float1, theArray[0]);
		assertEquals(float2, theArray[1]);
		assertEquals(float3, theArray[2]);
	}
	
	@Test
	public void testConvertEmptyListToArray(){
		// Setup the empty List
		List<Float> theList = new ArrayList<>();
		
		// Convert to array and verify
		float[] theArray = FloatUtil.convertListToArray(theList);
		assertEquals(0, theArray.length);
	}
}
