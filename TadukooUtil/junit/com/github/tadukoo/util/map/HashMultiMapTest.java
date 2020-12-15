package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashMultiMapTest{
	private HashMultiMap<String, String> map;
	
	@BeforeEach
	public void setup(){
		map = new HashMultiMap<>();
	}
	
	@Test
	public void testAsMapEmpty(){
		Map<String, List<String>> underMap = map.asMap();
		assertNotNull(underMap);
		assertTrue(underMap.isEmpty());
	}
	
	@Test
	public void testAsMapNotEmpty(){
		map.put("Test", "Yes");
		map.put("Test2", "Maybe");
		map.put("Test2", "No");
		
		Map<String, List<String>> underMap = map.asMap();
		assertNotNull(underMap);
		assertEquals(2, underMap.keySet().size());
		List<String> test = underMap.get("Test");
		assertEquals(1, test.size());
		assertEquals("Yes", test.get(0));
		List<String> test2 = underMap.get("Test2");
		assertEquals(2, test2.size());
		assertEquals("Maybe", test2.get(0));
		assertEquals("No", test2.get(1));
	}
	
	@Test
	public void testConstructorWithPairs(){
		// Initialize HashMultiMap with the Pairs
		map = new HashMultiMap<>(Pair.of("Test", "Yes"), Pair.of("Test2", "No"));
		
		// Verify the contents of the HashMultiMap
		assertNotNull(map);
		assertEquals(2, map.size());
		List<String> test = map.get("Test");
		assertEquals(1, test.size());
		assertEquals("Yes", test.get(0));
		List<String> test2 = map.get("Test2");
		assertEquals(1, test2.size());
		assertEquals("No", test2.get(0));
	}
	
	@Test
	public void testConstructorWithMap(){
		// Create a Map
		Map<String, String> otherMap = new HashMap<>();
		otherMap.put("Test", "Yes");
		otherMap.put("Test2", "No");
		
		// Initialize HashMultiMap with the map
		map = new HashMultiMap<>(otherMap);
		
		// Verify the contents of the HashMultiMap
		assertNotNull(map);
		assertEquals(2, map.size());
		List<String> test = map.get("Test");
		assertEquals(1, test.size());
		assertEquals("Yes", test.get(0));
		List<String> test2 = map.get("Test2");
		assertEquals(1, test2.size());
		assertEquals("No", test2.get(0));
	}
	
	@Test
	public void testConstructorWithMultiMap(){
		// Create a MultiMap
		MultiMap<String, String> otherMap = new HashMultiMap<>();
		otherMap.put("Test", "Yes");
		otherMap.put("Test", "No");
		otherMap.put("Test2", "Maybe");
		
		// Initialize HashMultiMap with the multimap
		map = new HashMultiMap<>(otherMap);
		
		// Verify the contents of the HashMultiMap
		assertNotNull(map);
		assertEquals(3, map.size());
		List<String> test = map.get("Test");
		assertEquals(2, test.size());
		assertEquals("Yes", test.get(0));
		assertEquals("No", test.get(1));
		List<String> test2 = map.get("Test2");
		assertEquals(1, test2.size());
		assertEquals("Maybe", test2.get(0));
	}
	
	@Test
	public void testEqualsOtherMultiMap(){
		MultiMap<String, String> otherMap = new TreeMultiMap<>();
		// Need to list map first so we call its equals method
		assertNotEquals(map, otherMap);
	}
	
	@Test
	public void testEqualsOtherHashMultiMap(){
		MultiMap<String, String> otherMap = new HashMultiMap<>();
		// Need to list map first so we call its equals method
		assertEquals(map, otherMap);
	}
	
	@Test
	public void testInitialCapacityConstructor(){
		map = new HashMultiMap<>(5);
		
		assertNotNull(map);
		assertTrue(map.isEmpty());
		// No way to check initial capacity
	}
	
	@Test
	public void testLoadFactorConstructor(){
		map = new HashMultiMap<>(5, 5);
		
		assertNotNull(map);
		assertTrue(map.isEmpty());
		// No way to check initial capacity or load factor
	}
}
