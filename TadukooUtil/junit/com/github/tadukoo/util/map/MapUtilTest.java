package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapUtilTest{
	
	/**
	 * Tests that {@link MapUtil#isBlank(Map)} works properly on null
	 */
	@Test
	public void testIsBlankNull(){
		assertTrue(MapUtil.isBlank(null));
	}
	
	/**
	 * Tests that {@link MapUtil#isBlank(Map)} works properly on an empty Map
	 */
	@Test
	public void testIsBlankEmptyMap(){
		assertTrue(MapUtil.isBlank(new HashMap<>()));
	}
	
	/**
	 * Tests that {@link MapUtil#isBlank(Map)} works properly on a populated Map
	 */
	@Test
	public void testIsBlankPopulatedMap(){
		Map<String, String> test = MapUtil.createMap(Pair.of("Test", "Value"));
		assertFalse(MapUtil.isBlank(test));
	}
	
	/**
	 * Tests that {@link MapUtil#isNotBlank(Map)} works properly on null
	 */
	@Test
	public void testIsNotBlankNull(){
		assertFalse(MapUtil.isNotBlank(null));
	}
	
	/**
	 * Tests that {@link MapUtil#isNotBlank(Map)} works properly on an empty Map
	 */
	@Test
	public void testIsNotBlankEmptyMap(){
		assertFalse(MapUtil.isNotBlank(new HashMap<>()));
	}
	
	/**
	 * Tests that {@link MapUtil#isNotBlank(Map)} works properly on a populated Map
	 */
	@Test
	public void testIsNotBlankPopulatedMap(){
		Map<String, String> test = MapUtil.createMap(Pair.of("Test", "Value"));
		assertTrue(MapUtil.isNotBlank(test));
	}
	
	/**
	 * Tests that {@link MapUtil#createMap(Pair[])} works properly
	 */
	@Test
	public void testCreateMap(){
		Map<String, String> map = MapUtil.createMap(Pair.of("Test", "Value"),
				Pair.of("Test2", "Other Value"),
				Pair.of("Derp", "Yes"));
		assertEquals(3, map.keySet().size());
		assertTrue(map.containsKey("Test"));
		assertEquals("Value", map.get("Test"));
		assertTrue(map.containsKey("Test2"));
		assertEquals("Other Value", map.get("Test2"));
		assertTrue(map.containsKey("Derp"));
		assertEquals("Yes", map.get("Derp"));
	}
}
