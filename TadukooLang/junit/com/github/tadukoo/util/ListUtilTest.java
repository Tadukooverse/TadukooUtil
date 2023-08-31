package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListUtilTest{
	
	/**
	 * Tests that {@link ListUtil#isBlank(List)} works properly on null
	 */
	@Test
	public void testIsBlankNull(){
		assertTrue(ListUtil.isBlank(null));
	}
	
	/**
	 * Tests that {@link ListUtil#isBlank(List)} works properly on an empty List
	 */
	@Test
	public void testIsBlankEmptyList(){
		assertTrue(ListUtil.isBlank(new ArrayList<>()));
	}
	
	/**
	 * Tests that {@link ListUtil#isBlank(List)} works properly on a populated List
	 */
	@Test
	public void testIsBlankPopulatedList(){
		List<String> test = ListUtil.createList("Test");
		assertFalse(ListUtil.isBlank(test));
	}
	
	/**
	 * Tests that {@link ListUtil#isNotBlank(List)} works properly on null
	 */
	@Test
	public void testIsNotBlankNull(){
		assertFalse(ListUtil.isNotBlank(null));
	}
	
	/**
	 * Tests that {@link ListUtil#isNotBlank(List)} works properly on an empty List
	 */
	@Test
	public void testIsNotBlankEmptyList(){
		assertFalse(ListUtil.isNotBlank(new ArrayList<>()));
	}
	
	/**
	 * Tests that {@link ListUtil#isNotBlank(List)} works properly on a populated List
	 */
	@Test
	public void testIsNotBlankPopulatedList(){
		List<String> test = ListUtil.createList("Test");
		assertTrue(ListUtil.isNotBlank(test));
	}
	
	/**
	 * Tests that {@link ListUtil#createList(Object[])} works properly in
	 * creating a list from some given items
	 */
	@Test
	public void testCreateList(){
		List<Integer> list = ListUtil.createList(5, 1, 2);
		assertEquals(3, list.size());
		assertEquals(5, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
	}
	
	/**
	 * Tests that {@link ListUtil#createList(Object[])} does allow for
	 * modifying the returned List, as the main purpose of using it
	 * over {@link Arrays#asList}
	 */
	@Test
	public void testCreateListAllowsModification(){
		List<String> list = ListUtil.createList("test", "junk", "here");
		assertEquals(3, list.size());
		
		// Remove an item
		list.remove(2);
		assertEquals(2, list.size());
		
		// Add a couple items
		list.add("yes");
		list.add("no");
		assertEquals(4, list.size());
	}
	
	@Test
	public void testMergeLists(){
		List<Integer> list = ListUtil.mergeLists(ListUtil.createList(5, 1, 2), ListUtil.createList(6, 3, 9));
		assertEquals(6, list.size());
		assertEquals(5, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(6, list.get(3));
		assertEquals(3, list.get(4));
		assertEquals(9, list.get(5));
	}
	
	@Test
	public void testMergeListsAllowsModification(){
		List<Integer> list = ListUtil.mergeLists(ListUtil.createList(5), ListUtil.createList(1, 2));
		assertEquals(3, list.size());
		
		// Remove an item
		list.remove(0);
		assertEquals(2, list.size());
		
		// Add a couple items
		list.add(7);
		list.add(9);
		assertEquals(4, list.size());
	}
}
