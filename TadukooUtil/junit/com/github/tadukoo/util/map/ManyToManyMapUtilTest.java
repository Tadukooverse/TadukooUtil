package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManyToManyMapUtilTest{
	
	/**
	 * Tests that {@link ManyToManyMapUtil#isBlank(ManyToManyMap)} works properly on null
	 */
	@Test
	public void testIsBlankNull(){
		assertTrue(ManyToManyMapUtil.isBlank(null));
	}
	
	/**
	 * Tests that {@link ManyToManyMapUtil#isBlank(ManyToManyMap)} works properly on an empty ManyToManyMap
	 */
	@Test
	public void testIsBlankEmptyManyToManyMap(){
		assertTrue(ManyToManyMapUtil.isBlank(new HashManyToManyMap<>()));
	}
	
	/**
	 * Tests that {@link ManyToManyMapUtil#isBlank(ManyToManyMap)} works properly on a populated ManyToManyMap
	 */
	@Test
	public void testIsBlankPopulatedManyToManyMap(){
		ManyToManyMap<String, String> test = new HashManyToManyMap<>(Pair.of("Test", "Value"));
		assertFalse(ManyToManyMapUtil.isBlank(test));
	}
	
	/**
	 * Tests that {@link ManyToManyMapUtil#isNotBlank(ManyToManyMap)} works properly on null
	 */
	@Test
	public void testIsNotBlankNull(){
		assertFalse(ManyToManyMapUtil.isNotBlank(null));
	}
	
	/**
	 * Tests that {@link ManyToManyMapUtil#isNotBlank(ManyToManyMap)} works properly on an empty ManyToManyMap
	 */
	@Test
	public void testIsNotBlankEmptyManyToManyMap(){
		assertFalse(ManyToManyMapUtil.isNotBlank(new HashManyToManyMap<>()));
	}
	
	/**
	 * Tests that {@link ManyToManyMapUtil#isNotBlank(ManyToManyMap)} works properly on a populated ManyToManyMap
	 */
	@Test
	public void testIsNotBlankPopulatedManyToManyMap(){
		ManyToManyMap<String, String> test = new HashManyToManyMap<>(Pair.of("Test", "Value"));
		assertTrue(ManyToManyMapUtil.isNotBlank(test));
	}
}
