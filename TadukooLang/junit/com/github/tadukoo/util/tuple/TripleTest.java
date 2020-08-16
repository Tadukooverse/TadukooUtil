package com.github.tadukoo.util.tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
