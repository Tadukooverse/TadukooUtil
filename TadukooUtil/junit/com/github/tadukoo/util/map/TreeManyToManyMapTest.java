package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeManyToManyMapTest{
	private TreeManyToManyMap<String, Integer> emptyMap;
	private TreeManyToManyMap<String, Integer> populatedMap;
	private Comparator<String> keyComparator;
	private Comparator<Integer> valueComparator;
	
	@BeforeEach
	public void setup(){
		emptyMap = new TreeManyToManyMap<>();
		
		populatedMap = new TreeManyToManyMap<>();
		populatedMap.put("Test", 82);
		populatedMap.put("Test", 5);
		populatedMap.put("Derp", 24);
		populatedMap.put("Plop", 5);
		
		keyComparator = Comparator.comparingInt(String::length);
		valueComparator = Comparator.naturalOrder();
	}
	
	@Test
	public void testDefaultConstructor(){
		assertNotNull(emptyMap);
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	public void testConstructorWithComparators(){
		emptyMap = new TreeManyToManyMap<>(keyComparator, valueComparator);
		assertEquals(keyComparator, emptyMap.keyComparator());
		assertEquals(valueComparator, emptyMap.valueComparator());
	}
	
	@Test
	public void testConstructorWithPairs(){
		// Initialize TreeMultiMap with the pairs
		emptyMap = new TreeManyToManyMap<>(Pair.of("Test", 5), Pair.of("Test2", 84));
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(2, emptyMap.size());
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.getValues("Test2");
		assertEquals(1, test2.size());
		assertEquals(84, test2.get(0));
	}
	
	@Test
	public void testConstructorWithMap(){
		// Create a Map
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test2", 84);
		
		// Initialize TreeMultiMap with the map
		emptyMap = new TreeManyToManyMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(2, emptyMap.size());
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.getValues("Test2");
		assertEquals(1, test2.size());
		assertEquals(84, test2.get(0));
	}
	
	@Test
	public void testConstructorWithSortedMap(){
		// Create a Map
		SortedMap<String, Integer> otherMap = new TreeMap<>(keyComparator);
		otherMap.put("Test", 5);
		otherMap.put("Test2", 84);
		
		// Initialize TreeMultiMap with the map
		emptyMap = new TreeManyToManyMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(2, emptyMap.size());
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.getValues("Test2");
		assertEquals(1, test2.size());
		assertEquals(84, test2.get(0));
		assertEquals(keyComparator, emptyMap.keyComparator());
	}
	
	@Test
	public void testConstructorWithMultiMap(){
		// Create a MultiMap
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 84);
		otherMap.put("Test2", 24);
		
		// Initialize TreeMultiMap with the multimap
		emptyMap = new TreeManyToManyMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(3, emptyMap.size());
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		List<Integer> test2 = emptyMap.getValues("Test2");
		assertEquals(1, test2.size());
		assertEquals(24, test2.get(0));
	}
	
	@Test
	public void testManyToManyMapConstructor(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		otherMap.put("Plop", 5);
		
		emptyMap = new TreeManyToManyMap<>(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(3, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(emptyMap.containsKey("Plop"));
		List<Integer> plop = emptyMap.getValues("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
	}
	
	@Test
	public void testEqualsOtherManyToManyMap(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		// Need to list map first so we call its equals method
		assertNotEquals(emptyMap, otherMap);
	}
	
	@Test
	public void testEqualsOtherTreeManyToManyMap(){
		ManyToManyMap<String, Integer> otherMap = new TreeManyToManyMap<>();
		// Need to list map first so we call its equals method
		assertEquals(emptyMap, otherMap);
	}
	
	@Test
	public void testKeyComparatorNoComparator(){
		assertNull(emptyMap.keyComparator());
	}
	
	@Test
	public void testValueComparatorNoComparator(){
		assertNull(emptyMap.valueComparator());
	}
	
	@Test
	public void testAsKeysToValuesMultiMap(){
		MultiMap<String, Integer> keysToValues = populatedMap.asKeysToValuesMultiMap();
		assertEquals(3, keysToValues.keySetSize());
		assertTrue(keysToValues.containsKey("Test"));
		assertTrue(keysToValues.containsKey("Derp"));
		assertTrue(keysToValues.containsKey("Plop"));
		List<Integer> test = keysToValues.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
		List<Integer> derp = keysToValues.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> plop = keysToValues.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
	}
	
	@Test
	public void testAsValuesToKeysMultiMap(){
		MultiMap<Integer, String> valuesToKeys = populatedMap.asValuesToKeysMultiMap();
		assertEquals(3, valuesToKeys.keySetSize());
		assertTrue(valuesToKeys.containsKey(82));
		assertTrue(valuesToKeys.containsKey(5));
		assertTrue(valuesToKeys.containsKey(24));
		List<String> test82 = valuesToKeys.get(82);
		assertEquals(1, test82.size());
		assertEquals("Test", test82.get(0));
		List<String> test5 = valuesToKeys.get(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Plop", test5.get(1));
		List<String> test24 = valuesToKeys.get(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testFirstKey(){
		assertEquals("Derp", populatedMap.firstKey());
	}
	
	@Test
	public void testFirstKeyEmpty(){
		try{
			emptyMap.firstKey();
			fail();
		}catch(NoSuchElementException e){
			// Good
		}
	}
	
	@Test
	public void testLastKey(){
		assertEquals("Test", populatedMap.lastKey());
	}
	
	@Test
	public void testLastKeyEmpty(){
		try{
			emptyMap.lastKey();
			fail();
		}catch(NoSuchElementException e){
			// Good
		}
	}
	
	@Test
	public void testFirstEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.firstEntry();
		assertEquals("Derp", entry.getKey());
		List<Integer> derp = entry.getValue();
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		
		// Make sure it doesn't modify the multimap
		assertEquals(4, populatedMap.size());
	}
	
	@Test
	public void testFirstEntryEmpty(){
		assertNull(emptyMap.firstEntry());
	}
	
	@Test
	public void testLastEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.lastEntry();
		assertEquals("Test", entry.getKey());
		List<Integer> test = entry.getValue();
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
		
		// Make sure it doesn't modify the multimap
		assertEquals(4, populatedMap.size());
	}
	
	@Test
	public void testLastEntryEmpty(){
		assertNull(emptyMap.lastEntry());
	}
	
	@Test
	public void testPollFirstEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.pollFirstEntry();
		assertEquals("Derp", entry.getKey());
		List<Integer> derp = entry.getValue();
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		
		// Make sure it removed it
		assertFalse(populatedMap.containsKey("Derp"));
	}
	
	@Test
	public void testPollFirstEntryEmpty(){
		assertNull(emptyMap.pollFirstEntry());
	}
	
	@Test
	public void testPollLastEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.pollLastEntry();
		assertEquals("Test", entry.getKey());
		List<Integer> test = entry.getValue();
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
		
		// Make sure it removed it
		assertFalse(populatedMap.containsKey("Test"));
	}
	
	@Test
	public void testPollLastEntryEmpty(){
		assertNull(emptyMap.pollLastEntry());
	}
	
	@Test
	public void testLowerKey(){
		assertEquals("Plop", populatedMap.lowerKey("Test"));
	}
	
	@Test
	public void testLowerKeyLowest(){
		assertNull(populatedMap.lowerKey("Derp"));
	}
	
	@Test
	public void testHigherKey(){
		assertEquals("Plop", populatedMap.higherKey("Derp"));
	}
	
	@Test
	public void testHigherKeyHighest(){
		assertNull(populatedMap.higherKey("Test"));
	}
	
	@Test
	public void testLowerEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.lowerEntry("Plop");
		assertEquals("Derp", entry.getKey());
		List<Integer> values = entry.getValue();
		assertEquals(1, values.size());
		assertEquals(24, values.get(0));
	}
	
	@Test
	public void testLowerEntryLowest(){
		assertNull(populatedMap.lowerEntry("Derp"));
	}
	
	@Test
	public void testHigherEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.higherEntry("Plop");
		assertEquals("Test", entry.getKey());
		List<Integer> values = entry.getValue();
		assertEquals(2, values.size());
		assertEquals(82, values.get(0));
		assertEquals(5, values.get(1));
	}
	
	@Test
	public void testHigherEntryHighest(){
		assertNull(populatedMap.higherEntry("Test"));
	}
	
	@Test
	public void testFloorKey(){
		assertEquals("Test", populatedMap.floorKey("Test"));
	}
	
	@Test
	public void testFloorKeyNull(){
		assertNull(populatedMap.floorKey("Dero"));
	}
	
	@Test
	public void testCeilingKey(){
		assertEquals("Derp", populatedMap.ceilingKey("Derp"));
	}
	
	@Test
	public void testCeilingKeyNull(){
		assertNull(populatedMap.ceilingKey("Tesy"));
	}
	
	@Test
	public void testFloorEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.floorEntry("Test");
		assertEquals("Test", entry.getKey());
		List<Integer> values = entry.getValue();
		assertEquals(2, values.size());
		assertEquals(82, values.get(0));
		assertEquals(5, values.get(1));
	}
	
	@Test
	public void testFloorEntryNull(){
		assertNull(populatedMap.floorEntry("Dero"));
	}
	
	@Test
	public void testCeilingEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.ceilingEntry("Derp");
		assertEquals("Derp", entry.getKey());
		List<Integer> values = entry.getValue();
		assertEquals(1, values.size());
		assertEquals(24, values.get(0));
	}
	
	@Test
	public void testCeilingEntryNull(){
		assertNull(populatedMap.ceilingEntry("Tesy"));
	}
	
	@Test
	public void testNavigableKeySet(){
		NavigableSet<String> theSet = populatedMap.navigableKeySet();
		assertEquals(3, theSet.size());
		Iterator<String> it = theSet.iterator();
		assertEquals("Derp", it.next());
		assertEquals("Plop", it.next());
		assertEquals("Test", it.next());
	}
	
	@Test
	public void testDescendingKeySet(){
		NavigableSet<String> theSet = populatedMap.descendingKeySet();
		assertEquals(3, theSet.size());
		Iterator<String> it = theSet.iterator();
		assertEquals("Test", it.next());
		assertEquals("Plop", it.next());
		assertEquals("Derp", it.next());
	}
	
	@Test
	public void testDescendingMap(){
		NavigableMap<String, List<Integer>> reverseMap = populatedMap.descendingMap();
		assertEquals("Test", reverseMap.firstKey());
		assertEquals("Derp", reverseMap.lastKey());
		assertEquals(3, reverseMap.keySet().size());
		List<Integer> derp = reverseMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> plop = reverseMap.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
		List<Integer> test = reverseMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
	
	@Test
	public void testSubMap(){
		// Note: start key is inclusive, end key is exclusive
		SortedMap<String, List<Integer>> subMap = populatedMap.subMap("Derp", "Plop");
		assertEquals(1, subMap.keySet().size());
		assertTrue(subMap.containsKey("Derp"));
		List<Integer> derp = subMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testSubMapExclusive(){
		SortedMap<String, List<Integer>> subMap = populatedMap.subMap("Derp", false, "Plop", false);
		assertTrue(subMap.isEmpty());
	}
	
	@Test
	public void testHeadMap(){
		// Note: toKey is exclusive
		SortedMap<String, List<Integer>> headMap = populatedMap.headMap("Plop");
		assertEquals(1, headMap.keySet().size());
		assertTrue(headMap.containsKey("Derp"));
		List<Integer> derp = headMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testHeadMapInclusive(){
		SortedMap<String, List<Integer>> headMap = populatedMap.headMap("Test", true);
		assertEquals(3, headMap.keySet().size());
		assertTrue(headMap.containsKey("Derp"));
		assertTrue(headMap.containsKey("Plop"));
		assertTrue(headMap.containsKey("Test"));
		List<Integer> derp = headMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> plop = headMap.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
		List<Integer> test = headMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
	
	@Test
	public void testTailMap(){
		// Note: key is inclusive
		SortedMap<String, List<Integer>> tailMap = populatedMap.tailMap("Derp");
		assertEquals(3, tailMap.keySet().size());
		assertTrue(tailMap.containsKey("Derp"));
		assertTrue(tailMap.containsKey("Plop"));
		assertTrue(tailMap.containsKey("Test"));
		List<Integer> derp = tailMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> plop = tailMap.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
		List<Integer> test = tailMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
	
	@Test
	public void testTailMapExclusive(){
		SortedMap<String, List<Integer>> tailMap = populatedMap.tailMap("Plop", false);
		assertEquals(1, tailMap.keySet().size());
		assertTrue(tailMap.containsKey("Test"));
		List<Integer> test = tailMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
}
