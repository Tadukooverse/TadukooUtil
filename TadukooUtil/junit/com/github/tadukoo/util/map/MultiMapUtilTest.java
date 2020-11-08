package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiMapUtilTest{
	
	/**
	 * Tests that {@link MultiMapUtil#isBlank(MultiMap)} works properly on null
	 */
	@Test
	public void testIsBlankNull(){
		assertTrue(MultiMapUtil.isBlank(null));
	}
	
	/**
	 * Tests that {@link MultiMapUtil#isBlank(MultiMap)} works properly on an empty MultiMap
	 */
	@Test
	public void testIsBlankEmptyMultiMap(){
		assertTrue(MultiMapUtil.isBlank(new HashMultiMap<>()));
	}
	
	/**
	 * Tests that {@link MultiMapUtil#isBlank(MultiMap)} works properly on a populated MultiMap
	 */
	@Test
	public void testIsBlankPopulatedMultiMap(){
		MultiMap<String, String> test = new HashMultiMap<>(Pair.of("Test", "Value"));
		assertFalse(MultiMapUtil.isBlank(test));
	}
	
	/**
	 * Tests that {@link MultiMapUtil#isNotBlank(MultiMap)} works properly on null
	 */
	@Test
	public void testIsNotBlankNull(){
		assertFalse(MultiMapUtil.isNotBlank(null));
	}
	
	/**
	 * Tests that {@link MultiMapUtil#isNotBlank(MultiMap)} works properly on an empty MultiMap
	 */
	@Test
	public void testIsNotBlankEmptyMultiMap(){
		assertFalse(MultiMapUtil.isNotBlank(new HashMultiMap<>()));
	}
	
	/**
	 * Tests that {@link MultiMapUtil#isNotBlank(MultiMap)} works properly on a populated MultiMap
	 */
	@Test
	public void testIsNotBlankPopulatedMultiMap(){
		MultiMap<String, String> test = new HashMultiMap<>(Pair.of("Test", "Value"));
		assertTrue(MultiMapUtil.isNotBlank(test));
	}
}
