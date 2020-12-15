package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TreeMultiMapTest{
	private TreeMultiMap<String, Integer> emptyMap;
	private TreeMultiMap<String, Integer> populatedMap;
	private Comparator<String> comparator;
	
	@BeforeEach
	public void setup(){
		emptyMap = new TreeMultiMap<>();
		
		populatedMap = new TreeMultiMap<>();
		populatedMap.put("Test", 82);
		populatedMap.put("Test", 5);
		populatedMap.put("Derp", 24);
		
		comparator = Comparator.comparingInt(String::length);
	}
	
	@Test
	public void testAsMapEmpty(){
		Map<String, List<Integer>> underMap = emptyMap.asMap();
		assertNotNull(underMap);
		assertTrue(underMap.isEmpty());
	}
	
	@Test
	public void testAsMapNotEmpty(){
		emptyMap.put("Test", 5);
		emptyMap.put("Test2", 82);
		emptyMap.put("Test2", 24);
		
		Map<String, List<Integer>> underMap = emptyMap.asMap();
		assertNotNull(underMap);
		assertEquals(2, underMap.keySet().size());
		List<Integer> test = underMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = underMap.get("Test2");
		assertEquals(2, test2.size());
		assertEquals(82, test2.get(0));
		assertEquals(24, test2.get(1));
	}
	
	@Test
	public void testConstructorWithComparator(){
		emptyMap = new TreeMultiMap<>(comparator);
		assertEquals(comparator, emptyMap.comparator());
	}
	
	@Test
	public void testConstructorWithPairs(){
		// Initialize HashMultiMap with the Pairs
		emptyMap = new TreeMultiMap<>(Pair.of("Test", 5), Pair.of("Test2", 84));
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(2, emptyMap.size());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.get("Test2");
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
		emptyMap = new TreeMultiMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(2, emptyMap.size());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(84, test2.get(0));
	}
	
	@Test
	public void testConstructorWithSortedMap(){
		// Create a Map
		SortedMap<String, Integer> otherMap = new TreeMap<>(comparator);
		otherMap.put("Test", 5);
		otherMap.put("Test2", 84);
		
		// Initialize TreeMultiMap with the map
		emptyMap = new TreeMultiMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(2, emptyMap.size());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(84, test2.get(0));
		assertEquals(comparator, emptyMap.comparator());
	}
	
	@Test
	public void testConstructorWithMultiMap(){
		// Create a MultiMap
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 84);
		otherMap.put("Test2", 24);
		
		// Initialize TreeMultiMap with the multimap
		emptyMap = new TreeMultiMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(3, emptyMap.size());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(24, test2.get(0));
	}
	
	@Test
	public void testConstructorWithTreeMultiMap(){
		// Create a MultiMap
		TreeMultiMap<String, Integer> otherMap = new TreeMultiMap<>(comparator);
		otherMap.put("Test", 5);
		otherMap.put("Test", 84);
		otherMap.put("Test2", 24);
		
		// Initialize TreeMultiMap with the multimap
		emptyMap = new TreeMultiMap<>(otherMap);
		
		// Verify the contents of the TreeMultiMap
		assertNotNull(emptyMap);
		assertEquals(3, emptyMap.size());
		List<Integer> test = emptyMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(84, test.get(1));
		List<Integer> test2 = emptyMap.get("Test2");
		assertEquals(1, test2.size());
		assertEquals(24, test2.get(0));
		assertEquals(comparator, emptyMap.comparator());
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
		assertEquals(3, populatedMap.size());
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
		assertEquals(3, populatedMap.size());
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
		assertEquals("Derp", populatedMap.lowerKey("Test"));
	}
	
	@Test
	public void testLowerKeyLowest(){
		assertNull(populatedMap.lowerKey("Derp"));
	}
	
	@Test
	public void testHigherKey(){
		assertEquals("Test", populatedMap.higherKey("Derp"));
	}
	
	@Test
	public void testHigherKeyHighest(){
		assertNull(populatedMap.higherKey("Test"));
	}
	
	@Test
	public void testLowerEntry(){
		Map.Entry<String, List<Integer>> entry = populatedMap.lowerEntry("Test");
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
		Map.Entry<String, List<Integer>> entry = populatedMap.higherEntry("Derp");
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
		assertEquals(2, theSet.size());
		Iterator<String> it = theSet.iterator();
		assertEquals("Derp", it.next());
		assertEquals("Test", it.next());
	}
	
	@Test
	public void testDescendingKeySet(){
		NavigableSet<String> theSet = populatedMap.descendingKeySet();
		assertEquals(2, theSet.size());
		Iterator<String> it = theSet.iterator();
		assertEquals("Test", it.next());
		assertEquals("Derp", it.next());
	}
	
	@Test
	public void testDescendingMap(){
		NavigableMap<String, List<Integer>> reverseMap = populatedMap.descendingMap();
		assertEquals("Test", reverseMap.firstKey());
		assertEquals("Derp", reverseMap.lastKey());
		assertEquals(2, reverseMap.keySet().size());
		List<Integer> derp = reverseMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> test = reverseMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
	
	@Test
	public void testSubMap(){
		// Note: start key is inclusive, end key is exclusive
		SortedMap<String, List<Integer>> subMap = populatedMap.subMap("Derp", "Test");
		assertEquals(1, subMap.keySet().size());
		assertTrue(subMap.containsKey("Derp"));
		List<Integer> derp = subMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testSubMapExclusive(){
		SortedMap<String, List<Integer>> subMap = populatedMap.subMap("Derp", false, "Test", false);
		assertTrue(subMap.isEmpty());
	}
	
	@Test
	public void testHeadMap(){
		// Note: toKey is exclusive
		SortedMap<String, List<Integer>> headMap = populatedMap.headMap("Test");
		assertEquals(1, headMap.keySet().size());
		assertTrue(headMap.containsKey("Derp"));
		List<Integer> derp = headMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testHeadMapInclusive(){
		SortedMap<String, List<Integer>> headMap = populatedMap.headMap("Test", true);
		assertEquals(2, headMap.keySet().size());
		assertTrue(headMap.containsKey("Derp"));
		assertTrue(headMap.containsKey("Test"));
		List<Integer> derp = headMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> test = headMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
	
	@Test
	public void testTailMap(){
		// Note: key is exclusive
		SortedMap<String, List<Integer>> tailMap = populatedMap.tailMap("Derp");
		assertEquals(2, tailMap.keySet().size());
		assertTrue(tailMap.containsKey("Derp"));
		assertTrue(tailMap.containsKey("Test"));
		List<Integer> derp = tailMap.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		List<Integer> test = tailMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
	
	@Test
	public void testTailMapExclusive(){
		SortedMap<String, List<Integer>> tailMap = populatedMap.tailMap("Derp", false);
		assertEquals(1, tailMap.keySet().size());
		assertTrue(tailMap.containsKey("Test"));
		List<Integer> test = tailMap.get("Test");
		assertEquals(2, test.size());
		assertEquals(82, test.get(0));
		assertEquals(5, test.get(1));
	}
}
