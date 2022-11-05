package com.github.tadukoo.util.tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TripleTest{
	private final String left = "Test";
	private final int middle = 15;
	private final String right = "Derp";
	private Triple<String, Integer, String> triple;
	
	@BeforeEach
	public void setup(){
		triple = Triple.of(left, middle, right);
	}
	
	@Test
	public void testGetLeft(){
		assertEquals(left, triple.getLeft());
	}
	
	@Test
	public void testSetLeft(){
		triple.setLeft("Doodle");
		assertEquals("Doodle", triple.getLeft());
	}
	
	@Test
	public void testGetMiddle(){
		assertEquals(middle, triple.getMiddle());
	}
	
	@Test
	public void testSetMiddle(){
		triple.setMiddle(105);
		assertEquals(105, triple.getMiddle());
	}
	
	@Test
	public void testGetRight(){
		assertEquals(right, triple.getRight());
	}
	
	@Test
	public void testSetRight(){
		triple.setRight("Test-A");
		assertEquals("Test-A", triple.getRight());
	}
	
	@Test
	public void testToString(){
		assertEquals("(Test, 15, Derp)", triple.toString());
	}
	
	/*
	 * Test Equals
	 */
	
	@Test
	public void testEquals(){
		assertEquals(Triple.of(42, "Yes", 25.0), Triple.of(42, "Yes", 25.0));
	}
	
	@Test
	public void testEqualsLeftNotEqual(){
		assertNotEquals(Triple.of(41, "Yes", 25.0), Triple.of(42, "Yes", 25.0));
	}
	
	@Test
	public void testEqualsMiddleNotEqual(){
		assertNotEquals(Triple.of(42, "No", 25.0), Triple.of(42, "Yes", 25.0));
	}
	
	@Test
	public void testEqualsRightNotEqual(){
		assertNotEquals(Triple.of(42, "Yes", 26.0), Triple.of(42, "Yes", 25.0));
	}
	
	@Test
	public void testEqualsNoneEqual(){
		assertNotEquals(Triple.of(41, "No", 27.0), Triple.of(42, "Yes", 25.0));
	}
	
	@Test
	public void testEqualsDifferentType(){
		//noinspection AssertBetweenInconvertibleTypes
		assertNotEquals(Triple.of(42, "Yes", 25.0), "something");
	}
	
	@Test
	public void testEqualsAllNull(){
		assertEquals(Triple.of(null, null, null), Triple.of(null, null, null));
	}
	
	@Test
	public void testEqualsFirstTripleAllNull(){
		assertNotEquals(Triple.of(null, null, null), Triple.of(true, 42, "yes"));
	}
	
	@Test
	public void testEqualsSecondTripleAllNull(){
		assertNotEquals(Triple.of(true, 42, "yes"), Triple.of(null, null, null));
	}
	
	@Test
	public void testEqualsAllLeftNull(){
		assertEquals(Triple.of(null, 42, "yes"), Triple.of(null, 42, "yes"));
	}
	
	@Test
	public void testEqualsAllMiddleNull(){
		assertEquals(Triple.of(true, null, "yes"), Triple.of(true, null, "yes"));
	}
	
	@Test
	public void testEqualsAllRightNull(){
		assertEquals(Triple.of(true, 42, null), Triple.of(true, 42, null));
	}
}
