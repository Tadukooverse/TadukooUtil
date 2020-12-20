package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashManyToManyMapTest{
	private HashManyToManyMap<String, Integer> map;
	
	@BeforeEach
	public void setup(){
		map = new HashManyToManyMap<>();
	}
	
	@Test
	public void testConstructor(){
		assertNotNull(map);
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void testInitialCapacityConstructor(){
		map = new HashManyToManyMap<>(5);
		
		assertNotNull(map);
		assertTrue(map.isEmpty());
		// No way to check initial capacity
	}
	
	@Test
	public void testLoadFactorConstructor(){
		map = new HashManyToManyMap<>(5, 5);
		
		assertNotNull(map);
		assertTrue(map.isEmpty());
		// No way to check initial capacity or load factor
	}
	
	@Test
	public void testPairsConstructor(){
		map = new HashManyToManyMap<>(Pair.of("Test", 5), Pair.of("Derp", 24));
		assertNotNull(map);
		assertFalse(map.isEmpty());
		assertEquals(2, map.keySetSize());
		assertTrue(map.containsKey("Test"));
		List<Integer> test = map.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		assertTrue(map.containsKey("Derp"));
		List<Integer> derp = map.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testMapConstructor(){
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Derp", 24);
		
		map = new HashManyToManyMap<>(otherMap);
		assertNotNull(map);
		assertFalse(map.isEmpty());
		assertEquals(2, map.keySetSize());
		assertTrue(map.containsKey("Test"));
		List<Integer> test = map.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		assertTrue(map.containsKey("Derp"));
		List<Integer> derp = map.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testMultiMapConstructor(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		
		map = new HashManyToManyMap<>(otherMap);
		assertNotNull(map);
		assertFalse(map.isEmpty());
		assertEquals(2, map.keySetSize());
		assertTrue(map.containsKey("Test"));
		List<Integer> test = map.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertTrue(map.containsKey("Derp"));
		List<Integer> derp = map.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testManyToManyMapConstructor(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		otherMap.put("Plop", 5);
		
		map = new HashManyToManyMap<>(otherMap);
		assertNotNull(map);
		assertFalse(map.isEmpty());
		assertEquals(3, map.keySetSize());
		assertTrue(map.containsKey("Test"));
		List<Integer> test = map.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertTrue(map.containsKey("Derp"));
		List<Integer> derp = map.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(map.containsKey("Plop"));
		List<Integer> plop = map.getValues("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
	}
	
	@Test
	public void testEqualsOtherManyToManyMap(){
		ManyToManyMap<String, Integer> otherMap = new TreeManyToManyMap<>();
		// Need to list map first so we call its equals method
		assertNotEquals(map, otherMap);
	}
	
	@Test
	public void testEqualsOtherHashManyToManyMap(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		// Need to list map first so we call its equals method
		assertEquals(map, otherMap);
	}
}
