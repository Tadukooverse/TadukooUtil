package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionUtilTest{
	
	/**
	 * Tests that {@link CollectionUtil#isBlank(Collection)} works properly on null
	 */
	@Test
	public void testIsBlankNull(){
		assertTrue(CollectionUtil.isBlank(null));
	}
	
	/**
	 * Tests that {@link CollectionUtil#isBlank(Collection)} works properly on an empty Collection
	 */
	@Test
	public void testIsBlankEmptyCollection(){
		assertTrue(CollectionUtil.isBlank(new HashSet<>()));
	}
	
	/**
	 * Tests that {@link CollectionUtil#isBlank(Collection)} works properly on a populated Collection
	 */
	@Test
	public void testIsBlankPopulatedCollection(){
		Set<String> test = new HashSet<>();
		test.add("Test");
		assertFalse(CollectionUtil.isBlank(test));
	}
	
	/**
	 * Tests that {@link CollectionUtil#isNotBlank(Collection)} works properly on null
	 */
	@Test
	public void testIsNotBlankNull(){
		assertFalse(CollectionUtil.isNotBlank(null));
	}
	
	/**
	 * Tests that {@link CollectionUtil#isNotBlank(Collection)} works properly on an empty Collection
	 */
	@Test
	public void testIsNotBlankEmptyCollection(){
		assertFalse(CollectionUtil.isNotBlank(new HashSet<>()));
	}
	
	/**
	 * Tests that {@link CollectionUtil#isNotBlank(Collection)} works properly on a populated Collection
	 */
	@Test
	public void testIsNotBlankPopulatedCollection(){
		Set<String> test = new HashSet<>();
		test.add("Test");
		assertTrue(CollectionUtil.isNotBlank(test));
	}
}
