package com.github.tadukoo.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtilTest{
	
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
}
