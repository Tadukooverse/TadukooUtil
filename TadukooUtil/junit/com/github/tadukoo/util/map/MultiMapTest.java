package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MultiMapTest{
	private MultiMap<String, Integer> emptyMap;
	private MultiMap<String, Integer> populatedMap;
	
	@BeforeEach
	public void setup(){
		emptyMap = new HashMultiMap<>();
		
		populatedMap = new HashMultiMap<>();
		populatedMap.put("Test", 5);
		populatedMap.put("Test", 84);
		populatedMap.put("Test2", 12);
	}
	
	@Test
	public void testDefaultConstructor(){
		assertNotNull(emptyMap);
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	public void testPairsConstructor(){
		emptyMap = new HashMultiMap<>(Pair.of("Test", 0), Pair.of("Plop", 5));
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(0, test.get(0));
		List<Integer> plop = emptyMap.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
	}
	
	@Test
	public void testMapConstructor(){
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 0);
		otherMap.put("Plop", 5);
		
		emptyMap = new HashMultiMap<>(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(0, test.get(0));
		List<Integer> plop = emptyMap.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
	}
	
	@Test
	public void testMultiMapConstructor(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test2", 15);
		otherMap.put("Test2", 19);
		
		emptyMap = new HashMultiMap<>(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(2, test2.size());
		assertEquals(15, test2.get(0));
		assertEquals(19, test2.get(1));
	}
	
	@Test
	public void testIsEmpty(){
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty(){
		emptyMap.put("Test", 5);
		assertFalse(emptyMap.isEmpty());
	}
	
	@Test
	public void testEqualsEmptyMap(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		assertEquals(emptyMap, otherMap);
	}
	
	@Test
	public void testEqualsPopulatedMap(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test2", 15);
		otherMap.put("Test2", 19);
		
		// Populate this map with the same values
		emptyMap.put("Test", 5);
		emptyMap.put("Test2", 15);
		emptyMap.put("Test2", 19);
		
		assertEquals(emptyMap, otherMap);
	}
	
	@Test
	public void testNotEquals(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test2", 15);
		otherMap.put("Test2", 19);
		
		assertNotEquals(emptyMap, otherMap);
	}
	
	@Test
	public void testNotEqualsRandomObject(){
		Object obj = "Test thing";
		assertNotEquals(emptyMap, obj);
	}
	
	@Test
	public void testContainsKey(){
		emptyMap.put("Test", 5);
		assertTrue(emptyMap.containsKey("Test"));
	}
	
	@Test
	public void testNotContainsKey(){
		assertFalse(emptyMap.containsKey("Test"));
	}
	
	@Test
	public void testContainsValue(){
		emptyMap.put("Test", 5);
		assertTrue(emptyMap.containsValue(5));
	}
	
	@Test
	public void testNotContainsValue(){
		assertFalse(emptyMap.containsValue(5));
	}
	
	@Test
	public void testGet(){
		List<Integer> test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
	}
	
	@Test
	public void testKeySet(){
		Set<String> keys = populatedMap.keySet();
		assertEquals(2, keys.size());
		assertTrue(keys.contains("Test"));
		assertTrue(keys.contains("Test2"));
	}
	
	@Test
	public void testValues(){
		List<Integer> values = populatedMap.values();
		assertEquals(3, values.size());
		assertTrue(values.contains(5));
		assertTrue(values.contains(84));
		assertTrue(values.contains(12));
	}
	
	@Test
	public void testAsMap(){
		Map<String, List<Integer>> theMap = populatedMap.asMap();
		assertNotNull(theMap);
		assertEquals(2, theMap.keySet().size());
		assertTrue(theMap.containsKey("Test"));
		assertTrue(theMap.containsKey("Test2"));
		List<Integer> test = theMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		List<Integer> test2 = theMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(12, test2.get(0));
	}
	
	@Test
	public void testPutEmpty(){
		emptyMap.put("Test", 10);
		assertEquals(1, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(10, test.get(0));
	}
	
	@Test
	public void testPutNonEmpty(){
		populatedMap.put("Test", 1000);
		List<Integer> test = populatedMap.get("Test");
		assertEquals(3, test.size());
		assertEquals(1000, test.get(2));
	}
	
	@Test
	public void testPutAllEmpty(){
		List<Integer> values = new ArrayList<>();
		values.add(5);
		values.add(28);
		values.add(75);
		
		emptyMap.putAll("Test", values);
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.get("Test");
		assertEquals(3, test.size());
		assertEquals(5, test.get(0));
		assertEquals(28, test.get(1));
		assertEquals(75, test.get(2));
	}
	
	@Test
	public void testPutAllNonEmpty(){
		List<Integer> values = new ArrayList<>();
		values.add(15);
		values.add(28);
		values.add(75);
		
		populatedMap.putAll("Test", values);
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(5, test.size());
		assertEquals(15, test.get(2));
		assertEquals(28, test.get(3));
		assertEquals(75, test.get(4));
	}
	
	@Test
	public void testPutAllPairsEmpty(){
		emptyMap.putAll(Pair.of("Test", 98), Pair.of("Test2", 192));
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		assertTrue(emptyMap.containsKey("Test2"));
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(98, test.get(0));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(192, test2.get(0));
	}
	
	@Test
	public void testPutAllPairsNonEmpty(){
		populatedMap.putAll(Pair.of("Test", 98), Pair.of("Test2", 192));
		assertEquals(2, populatedMap.keySetSize());
		assertTrue(populatedMap.containsKey("Test"));
		assertTrue(populatedMap.containsKey("Test2"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(3, test.size());
		assertEquals(98, test.get(2));
		List<Integer> test2 = populatedMap.get("Test2");
		assertEquals(2, test2.size());
		assertEquals(192, test2.get(1));
	}
	
	@Test
	public void testPutAllMapEmpty(){
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 98);
		otherMap.put("Test2", 192);
		
		emptyMap.putAll(otherMap);
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		assertTrue(emptyMap.containsKey("Test2"));
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(98, test.get(0));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(192, test2.get(0));
	}
	
	@Test
	public void testPutAllMapNonEmpty(){
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 98);
		otherMap.put("Test2", 192);
		
		populatedMap.putAll(otherMap);
		assertEquals(2, populatedMap.keySetSize());
		assertTrue(populatedMap.containsKey("Test"));
		assertTrue(populatedMap.containsKey("Test2"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(3, test.size());
		assertEquals(98, test.get(2));
		List<Integer> test2 = populatedMap.get("Test2");
		assertEquals(2, test2.size());
		assertEquals(192, test2.get(1));
	}
	
	@Test
	public void testPutAllMultiMapEmpty(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 98);
		otherMap.put("Test", 19283);
		otherMap.put("Test2", 192);
		
		emptyMap.putAll(otherMap);
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		assertTrue(emptyMap.containsKey("Test2"));
		List<Integer> test = emptyMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(98, test.get(0));
		assertEquals(19283, test.get(1));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(192, test2.get(0));
	}
	
	@Test
	public void testPutAllMultiMapNonEmpty(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 98);
		otherMap.put("Test", 19283);
		otherMap.put("Test2", 192);
		
		populatedMap.putAll(otherMap);
		assertEquals(2, populatedMap.keySetSize());
		assertTrue(populatedMap.containsKey("Test"));
		assertTrue(populatedMap.containsKey("Test2"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(4, test.size());
		assertEquals(98, test.get(2));
		assertEquals(19283, test.get(3));
		List<Integer> test2 = populatedMap.get("Test2");
		assertEquals(2, test2.size());
		assertEquals(192, test2.get(1));
	}
	
	@Test
	public void testRemove(){
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		
		assertTrue(populatedMap.remove("Test", 84));
		
		assertTrue(populatedMap.containsKey("Test"));
		test = populatedMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
	}
	
	@Test
	public void testRemoveEmptyKey(){
		assertFalse(populatedMap.containsKey("Derp"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertFalse(populatedMap.remove("Derp", 5));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.values().size());
	}
	
	@Test
	public void testRemoveLastValueForKey(){
		assertTrue(populatedMap.containsKey("Test2"));
		assertEquals(1, populatedMap.get("Test2").size());
		assertTrue(populatedMap.remove("Test2", 12));
		assertFalse(populatedMap.containsKey("Test2"));
	}
	
	@Test
	public void testRemoveKey(){
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.removeKey("Test");
		assertFalse(populatedMap.containsKey("Test"));
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
	}
	
	@Test
	public void testRemoveKeyEmptyKey(){
		assertFalse(populatedMap.containsKey("Derp"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertNull(populatedMap.removeKey("Derp"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.values().size());
	}
	
	@Test
	public void testRemoveEntireList(){
		assertTrue(populatedMap.containsKey("Test"));
		assertEquals(2, populatedMap.get("Test").size());
		assertTrue(populatedMap.removeEntireList("Test", Arrays.asList(5, 84)));
		assertFalse(populatedMap.containsKey("Test"));
	}
	
	@Test
	public void testRemoveEntireListEmptyKey(){
		assertFalse(populatedMap.containsKey("Derp"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertFalse(populatedMap.removeEntireList("Derp", Arrays.asList(5, 84)));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
	}
	
	@Test
	public void testRemoveEntireListNonMatchingList(){
		assertTrue(populatedMap.containsKey("Test"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertFalse(populatedMap.removeEntireList("Test", Arrays.asList(15, 93, 24)));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
	}
	
	@Test
	public void testReplace(){
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		assertTrue(populatedMap.replace("Test", 5, 102));
		test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(84, test.get(0));
		assertEquals(102, test.get(1));
	}
	
	@Test
	public void testReplaceEmptyKey(){
		assertFalse(populatedMap.containsKey("Derp"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertFalse(populatedMap.replace("Derp", 5, 84));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
	}
	
	@Test
	public void testReplaceMissingValue(){
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		assertFalse(populatedMap.replace("Test", 15, 102));
		test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
	}
	
	@Test
	public void testReplaceEntireList(){
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		assertTrue(populatedMap.replaceEntireList("Test", Arrays.asList(5, 84), Arrays.asList(18, 24)));
		test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(18, test.get(0));
		assertEquals(24, test.get(1));
	}
	
	@Test
	public void testReplaceEntireListEmptyKey(){
		assertFalse(populatedMap.containsKey("Derp"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertFalse(populatedMap.replaceEntireList("Derp", Arrays.asList(5, 84), Arrays.asList(18, 24)));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
	}
	
	@Test
	public void testReplaceEntireListNonMatchingList(){
		assertTrue(populatedMap.containsKey("Test"));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		assertFalse(populatedMap.replaceEntireList("Test", Arrays.asList(15, 93, 24), Arrays.asList(18, 24)));
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
	}
	
	@Test
	public void testReplaceEntireListSingleListParam(){
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		List<Integer> oldTest = populatedMap.replaceEntireList("Test", Arrays.asList(18, 24));
		assertEquals(2, oldTest.size());
		assertEquals(5, oldTest.get(0));
		assertEquals(84, oldTest.get(1));
		test = populatedMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(18, test.get(0));
		assertEquals(24, test.get(1));
	}
	
	@Test
	public void testKeySetSize(){
		assertEquals(2, populatedMap.keySetSize());
	}
	
	@Test
	public void testSize(){
		assertEquals(3, populatedMap.size());
	}
	
	@Test
	public void testClear(){
		assertFalse(populatedMap.isEmpty());
		assertEquals(2, populatedMap.keySetSize());
		assertEquals(3, populatedMap.size());
		populatedMap.clear();
		assertTrue(populatedMap.isEmpty());
		assertEquals(0, populatedMap.keySetSize());
		assertEquals(0, populatedMap.size());
	}
}
