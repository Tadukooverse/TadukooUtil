package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetUtilTest{
	
	/**
	 * Tests that {@link SetUtil#isBlank(Set)} works properly on null
	 */
	@Test
	public void testIsBlankNull(){
		assertTrue(SetUtil.isBlank(null));
	}
	
	/**
	 * Tests that {@link SetUtil#isBlank(Set)} works properly on an empty Set
	 */
	@Test
	public void testIsBlankEmptySet(){
		assertTrue(SetUtil.isBlank(new HashSet<>()));
	}
	
	/**
	 * Tests that {@link SetUtil#isBlank(Set)} works properly on a populated Set
	 */
	@Test
	public void testIsBlankPopulatedSet(){
		Set<String> test = SetUtil.createSet("Test");
		assertFalse(SetUtil.isBlank(test));
	}
	
	/**
	 * Tests that {@link SetUtil#isNotBlank(Set)} works properly on null
	 */
	@Test
	public void testIsNotBlankNull(){
		assertFalse(SetUtil.isNotBlank(null));
	}
	
	/**
	 * Tests that {@link SetUtil#isNotBlank(Set)} works properly on an empty Set
	 */
	@Test
	public void testIsNotBlankEmptySet(){
		assertFalse(SetUtil.isNotBlank(new HashSet<>()));
	}
	
	/**
	 * Tests that {@link SetUtil#isNotBlank(Set)} works properly on a populated Set
	 */
	@Test
	public void testIsNotBlankPopulatedSet(){
		Set<String> test = SetUtil.createSet("Test");
		assertTrue(SetUtil.isNotBlank(test));
	}
	
	/**
	 * Tests that {@link SetUtil#createSet(Object[])} works properly in
	 * creating a Set from some given items
	 */
	@Test
	public void testCreateSet(){
		Set<Integer> set = SetUtil.createSet(5, 1, 2);
		assertEquals(3, set.size());
		assertTrue(set.contains(5));
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
	}
	
	/**
	 * Tests that {@link SetUtil#createSet(Object[])} does allow for
	 * modifying the returned Set
	 */
	@Test
	public void testCreateSetAllowsModification(){
		Set<String> set = SetUtil.createSet("test", "junk", "here");
		assertEquals(3, set.size());
		
		// Remove an item
		set.remove("here");
		assertEquals(2, set.size());
		
		// Add a couple items
		set.add("yes");
		set.add("no");
		assertEquals(4, set.size());
	}
	
	@Test
	public void testCreateOrderedSet(){
		Set<Integer> set = SetUtil.createOrderedSet(5, 1, 2);
		assertEquals(3, set.size());
		Iterator<Integer> it = set.iterator();
		assertEquals(5, it.next());
		assertEquals(1, it.next());
		assertEquals(2, it.next());
	}
	
	@Test
	public void testMergeSets(){
		Set<Integer> set = SetUtil.mergeSets(SetUtil.createSet(5, 1, 2), SetUtil.createSet(7, 3, 9));
		assertEquals(6, set.size());
		assertTrue(set.contains(5));
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(7));
		assertTrue(set.contains(3));
		assertTrue(set.contains(9));
	}
	
	@Test
	public void testMergeOrderedSets(){
		Set<Integer> set = SetUtil.mergeOrderedSets(SetUtil.createOrderedSet(5, 1, 2),
				SetUtil.createOrderedSet(7, 3, 9));
		assertEquals(6, set.size());
		Iterator<Integer> it = set.iterator();
		assertEquals(5, it.next());
		assertEquals(1, it.next());
		assertEquals(2, it.next());
		assertEquals(7, it.next());
		assertEquals(3, it.next());
		assertEquals(9, it.next());
	}
	
	@Test
	public void testMergeSetsWithBooleanFalse(){
		Set<Integer> set = SetUtil.mergeSets(false, SetUtil.createSet(5, 1, 2),
				SetUtil.createSet(7, 3, 9));
		assertEquals(6, set.size());
		assertTrue(set.contains(5));
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(7));
		assertTrue(set.contains(3));
		assertTrue(set.contains(9));
	}
	
	@Test
	public void testMergeSetsWithBooleanTrue(){
		Set<Integer> set = SetUtil.mergeSets(true, SetUtil.createOrderedSet(5, 1, 2),
				SetUtil.createOrderedSet(7, 3, 9));
		assertEquals(6, set.size());
		Iterator<Integer> it = set.iterator();
		assertEquals(5, it.next());
		assertEquals(1, it.next());
		assertEquals(2, it.next());
		assertEquals(7, it.next());
		assertEquals(3, it.next());
		assertEquals(9, it.next());
	}
}
