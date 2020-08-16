package com.github.tadukoo.util.tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
