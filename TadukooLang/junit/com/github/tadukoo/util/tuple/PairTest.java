package com.github.tadukoo.util.tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PairTest{
	private final String key = "Test";
	private final int value = 5;
	private Pair<String, Integer> pair;
	
	@BeforeEach
	public void setup(){
		pair = Pair.of(key, value);
	}
	
	@Test
	public void testGetLeft(){
		assertEquals(key, pair.getLeft());
	}
	
	@Test
	public void testGetKey(){
		assertEquals(key, pair.getKey());
	}
	
	@Test
	public void testSetLeft(){
		pair.setLeft("Derp");
		assertEquals("Derp", pair.getLeft());
	}
	
	@Test
	public void testSetKey(){
		pair.setKey("Derp");
		assertEquals("Derp", pair.getKey());
	}
	
	@Test
	public void testGetRight(){
		assertEquals(value, pair.getRight());
	}
	
	@Test
	public void testGetValue(){
		assertEquals(value, pair.getValue());
	}
	
	@Test
	public void testSetRight(){
		pair.setRight(10);
		assertEquals(10, pair.getRight());
	}
	
	@Test
	public void testSetValue(){
		pair.setValue(15);
		assertEquals(15, pair.getValue());
	}
	
	@Test
	public void testToString(){
		assertEquals("(Test, 5)", pair.toString());
	}
	
	/*
	 * Test Equals
	 */
	
	@Test
	public void testEquals(){
		assertEquals(Pair.of(42, "yes"), Pair.of(42, "yes"));
	}
	
	@Test
	public void testEqualsLeftNotEqual(){
		assertNotEquals(Pair.of(41, "yes"), Pair.of(42, "yes"));
	}
	
	@Test
	public void testEqualsRightNotEqual(){
		assertNotEquals(Pair.of(42, "yes"), Pair.of(42, "no"));
	}
	
	@Test
	public void testEqualsNeitherEqual(){
		assertNotEquals(Pair.of(41, "yes"), Pair.of(42, "no"));
	}
	
	@Test
	public void testEqualsDifferentType(){
		//noinspection AssertBetweenInconvertibleTypes
		assertNotEquals(Pair.of(42, "yes"), 42);
	}
	
	@Test
	public void testEqualsAllNull(){
		assertEquals(Pair.of(null, null), Pair.of(null, null));
	}
	
	@Test
	public void testEqualsAllFirstPairNull(){
		assertNotEquals(Pair.of(null, null), Pair.of(true, 42));
	}
	
	@Test
	public void testEqualsAllSecondPairNull(){
		assertNotEquals(Pair.of(true, 42), Pair.of(null, null));
	}
	
	@Test
	public void testEqualsLeftsNull(){
		assertEquals(Pair.of(null, 42), Pair.of(null, 42));
	}
	
	@Test
	public void testEqualsRightsNull(){
		assertEquals(Pair.of(true, null), Pair.of(true, null));
	}
}
