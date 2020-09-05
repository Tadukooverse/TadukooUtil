package com.github.tadukoo.util.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ManyToManyMapTest{
	private ManyToManyMap<String, Integer> emptyMap;
	private ManyToManyMap<String, Integer> populatedMap;
	
	@BeforeEach
	public void setup(){
		emptyMap = new HashManyToManyMap<>();
		
		populatedMap = new HashManyToManyMap<>();
		populatedMap.put("Test", 5);
		populatedMap.put("Test", 82);
		populatedMap.put("Derp", 24);
		populatedMap.put("Plop", 5);
	}
	
	@Test
	public void testDefaultConstructor(){
		assertNotNull(emptyMap);
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	public void testMapConstructor(){
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Derp", 24);
		
		emptyMap = new HashManyToManyMap<>(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testMultiMapConstructor(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		
		emptyMap = new HashManyToManyMap<>(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
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
		
		emptyMap = new HashManyToManyMap<>(otherMap);
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
	public void testIsEmpty(){
		assertTrue(emptyMap.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty(){
		assertFalse(populatedMap.isEmpty());
	}
	
	@Test
	public void testEqualsEmptyMap(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		assertEquals(emptyMap, otherMap);
	}
	
	@Test
	public void testEqualsPopulatedMap(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		otherMap.put("Plop", 5);
		
		assertEquals(populatedMap, otherMap);
	}
	
	@Test
	public void testNotEquals(){
		assertNotEquals(emptyMap, populatedMap);
	}
	
	@Test
	public void testNotEqualsRandomObject(){
		Object obj = "Test thing";
		assertNotEquals(emptyMap, obj);
	}
	
	@Test
	public void testContainsKey(){
		emptyMap.put("Test", 12);
		assertTrue(emptyMap.containsKey("Test"));
	}
	
	@Test
	public void testNotContainsKey(){
		assertFalse(emptyMap.containsKey("Test"));
	}
	
	@Test
	public void testContainsValue(){
		emptyMap.put("Test", 12);
		assertTrue(emptyMap.containsValue(12));
	}
	
	@Test
	public void testNotContainsValue(){
		assertFalse(emptyMap.containsValue(12));
	}
	
	@Test
	public void testGetKeys(){
		List<String> keys = populatedMap.getKeys(5);
		assertEquals(2, keys.size());
		assertEquals("Test", keys.get(0));
		assertEquals("Plop", keys.get(1));
	}
	
	@Test
	public void testGetValues(){
		List<Integer> values = populatedMap.getValues("Test");
		assertEquals(2, values.size());
		assertEquals(5, values.get(0));
		assertEquals(82, values.get(1));
	}
	
	@Test
	public void testKeySet(){
		Set<String> keys = populatedMap.keySet();
		assertEquals(3, keys.size());
		assertTrue(keys.contains("Test"));
		assertTrue(keys.contains("Derp"));
		assertTrue(keys.contains("Plop"));
	}
	
	@Test
	public void testValueSet(){
		Set<Integer> values = populatedMap.valueSet();
		assertEquals(3, values.size());
		assertTrue(values.contains(5));
		assertTrue(values.contains(82));
		assertTrue(values.contains(24));
	}
	
	@Test
	public void testKeysToValues(){
		MultiMap<String, Integer> keysToValues = populatedMap.keysToValues();
		assertNotNull(keysToValues);
		assertFalse(keysToValues.isEmpty());
		assertEquals(3, keysToValues.keySetSize());
		assertTrue(keysToValues.containsKey("Test"));
		List<Integer> test = keysToValues.get("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertTrue(keysToValues.containsKey("Derp"));
		List<Integer> derp = keysToValues.get("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(keysToValues.containsKey("Plop"));
		List<Integer> plop = keysToValues.get("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
	}
	
	@Test
	public void testValuesToKeys(){
		MultiMap<Integer, String> valuesToKeys = populatedMap.valuesToKeys();
		assertNotNull(valuesToKeys);
		assertFalse(valuesToKeys.isEmpty());
		assertEquals(3, valuesToKeys.keySetSize());
		assertTrue(valuesToKeys.containsKey(5));
		List<String> test5 = valuesToKeys.get(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Plop", test5.get(1));
		assertTrue(valuesToKeys.containsKey(82));
		List<String> test82 = valuesToKeys.get(82);
		assertEquals(1, test82.size());
		assertEquals("Test", test82.get(0));
		assertTrue(valuesToKeys.containsKey(24));
		List<String> test24 = valuesToKeys.get(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testPut(){
		emptyMap.put("Test", 25);
		assertFalse(emptyMap.isEmpty());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(25, test.get(0));
		assertTrue(emptyMap.containsValue(25));
		List<String> test25 = emptyMap.getKeys(25);
		assertEquals(1, test25.size());
		assertEquals("Test", test25.get(0));
	}
	
	@Test
	public void testPutAllKeys(){
		emptyMap.putAllKeys(5, Arrays.asList("Test", "Derp"));
		assertFalse(emptyMap.isEmpty());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(5, derp.get(0));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Derp", test5.get(1));
	}
	
	@Test
	public void testPutAllValues(){
		emptyMap.putAllValues("Test", Arrays.asList(5, 24));
		assertFalse(emptyMap.isEmpty());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(24, test.get(1));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Test", test5.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Test", test24.get(0));
	}
	
	@Test
	public void testPutAllKeyValMappingsMap(){
		Map<String, Integer> otherMap = new HashMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Derp", 24);
		
		emptyMap.putAllKeyValMappings(otherMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Test", test5.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testPutAllValKeyMappingsMap(){
		Map<Integer, String> otherMap = new HashMap<>();
		otherMap.put(5, "Test");
		otherMap.put(24, "Derp");
		
		emptyMap.putAllValKeyMappings(otherMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(5, test.get(0));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Test", test5.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testPutAllKeyValMappingsMultiMap(){
		MultiMap<String, Integer> otherMap = new HashMultiMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		
		emptyMap.putAllKeyValMappings(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Test", test5.get(0));
		assertTrue(emptyMap.containsValue(82));
		List<String> test82 = emptyMap.getKeys(82);
		assertEquals(1, test82.size());
		assertEquals("Test", test82.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testPutAllValKeyMappingsMultiMap(){
		MultiMap<Integer, String> otherMap = new HashMultiMap<>();
		otherMap.put(5, "Test");
		otherMap.put(82, "Test");
		otherMap.put(24, "Derp");
		
		emptyMap.putAllValKeyMappings(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(2, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertTrue(test.contains(5));
		assertTrue(test.contains(82));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertTrue(derp.contains(24));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Test", test5.get(0));
		assertTrue(emptyMap.containsValue(82));
		List<String> test82 = emptyMap.getKeys(82);
		assertEquals(1, test82.size());
		assertEquals("Test", test82.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testPutAllKeyValMappingsManyToManyMap(){
		ManyToManyMap<String, Integer> otherMap = new HashManyToManyMap<>();
		otherMap.put("Test", 5);
		otherMap.put("Test", 82);
		otherMap.put("Derp", 24);
		otherMap.put("Plop", 5);
		
		emptyMap.putAllKeyValMappings(otherMap);
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
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Plop", test5.get(1));
		assertTrue(emptyMap.containsValue(82));
		List<String> test82 = emptyMap.getKeys(82);
		assertEquals(1, test82.size());
		assertEquals("Test", test82.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testPutAllValKeyMappingsManyToManyMap(){
		ManyToManyMap<Integer, String> otherMap = new HashManyToManyMap<>();
		otherMap.put(5, "Test");
		otherMap.put(82, "Test");
		otherMap.put(24, "Derp");
		otherMap.put(5, "Plop");
		
		emptyMap.putAllValKeyMappings(otherMap);
		assertNotNull(emptyMap);
		assertFalse(emptyMap.isEmpty());
		assertEquals(3, emptyMap.keySetSize());
		assertTrue(emptyMap.containsKey("Test"));
		List<Integer> test = emptyMap.getValues("Test");
		assertEquals(2, test.size());
		assertTrue(test.contains(5));
		assertTrue(test.contains(82));
		assertTrue(emptyMap.containsKey("Derp"));
		List<Integer> derp = emptyMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
		assertTrue(emptyMap.containsKey("Plop"));
		List<Integer> plop = emptyMap.getValues("Plop");
		assertEquals(1, plop.size());
		assertEquals(5, plop.get(0));
		assertTrue(emptyMap.containsValue(5));
		List<String> test5 = emptyMap.getKeys(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Plop", test5.get(1));
		assertTrue(emptyMap.containsValue(82));
		List<String> test82 = emptyMap.getKeys(82);
		assertEquals(1, test82.size());
		assertEquals("Test", test82.get(0));
		assertTrue(emptyMap.containsValue(24));
		List<String> test24 = emptyMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testRemoveKey(){
		List<Integer> test = populatedMap.removeKey("Test");
		assertEquals(2, test.size());
		assertEquals(5, test.get(0));
		assertEquals(82, test.get(1));
		assertFalse(populatedMap.containsKey("Test"));
		assertTrue(populatedMap.containsValue(5));
		assertFalse(populatedMap.containsValue(82));
		List<String> test5 = populatedMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Plop", test5.get(0));
	}
	
	@Test
	public void testRemoveEmptyKey(){
		List<Integer> test = emptyMap.removeKey("Test");
		assertNull(test);
	}
	
	@Test
	public void testRemoveValue(){
		List<String> test5 = populatedMap.removeValue(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Plop", test5.get(1));
		assertFalse(populatedMap.containsValue(5));
		assertTrue(populatedMap.containsKey("Test"));
		assertFalse(populatedMap.containsKey("Plop"));
		List<Integer> test = populatedMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(82, test.get(0));
	}
	
	@Test
	public void testRemoveEmptyValue(){
		List<String> test5 = emptyMap.removeValue(5);
		assertNull(test5);
	}
	
	@Test
	public void testRemove(){
		assertTrue(populatedMap.remove("Test", 5));
		assertTrue(populatedMap.containsKey("Test"));
		List<Integer> test = populatedMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(82, test.get(0));
		assertTrue(populatedMap.containsValue(5));
		List<String> test5 = populatedMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Plop", test5.get(0));
	}
	
	@Test
	public void testRemoveNonExistentMapping(){
		assertFalse(populatedMap.remove("Test", 39));
	}
	
	@Test
	public void testRemoveEmpty(){
		assertFalse(emptyMap.remove("Test", 5));
	}
	
	@Test
	public void testRemoveEntireKeysList(){
		assertTrue(populatedMap.removeEntireKeysList(5, Arrays.asList("Test", "Plop")));
		assertFalse(populatedMap.containsValue(5));
		assertTrue(populatedMap.containsKey("Test"));
		assertFalse(populatedMap.containsKey("Plop"));
		List<Integer> test = populatedMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(82, test.get(0));
	}
	
	@Test
	public void testRemoveEntireKeysListNonMatchingList(){
		assertFalse(populatedMap.removeEntireKeysList(5, Collections.singletonList("Derp")));
	}
	
	@Test
	public void testRemoveEntireKeysListEmpty(){
		assertFalse(emptyMap.removeEntireKeysList(5, Arrays.asList("Test", "Plop")));
	}
	
	@Test
	public void testRemoveEntireValuesList(){
		assertTrue(populatedMap.removeEntireValuesList("Test", Arrays.asList(5, 82)));
		assertFalse(populatedMap.containsKey("Test"));
		assertTrue(populatedMap.containsValue(5));
		assertFalse(populatedMap.containsValue(82));
		List<String> test5 = populatedMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Plop", test5.get(0));
	}
	
	@Test
	public void testRemoveEntireValuesListNonMatchingList(){
		assertFalse(populatedMap.removeEntireValuesList("Test", Collections.singletonList(39)));
	}
	
	@Test
	public void testRemoveEntireValuesListEmpty(){
		assertFalse(emptyMap.removeEntireValuesList("Test", Arrays.asList(5, 82)));
	}
	
	@Test
	public void testReplaceEntireKeyList(){
		List<String> oldKeys = populatedMap.replaceEntireKeyList(5, Collections.singletonList("Derp"));
		assertEquals(2, oldKeys.size());
		assertEquals("Test", oldKeys.get(0));
		assertEquals("Plop", oldKeys.get(1));
		assertTrue(populatedMap.containsKey("Test"));
		assertFalse(populatedMap.containsKey("Plop"));
		List<Integer> test = populatedMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(82, test.get(0));
		List<String> test5 = populatedMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Derp", test5.get(0));
		assertTrue(populatedMap.containsKey("Derp"));
		List<Integer> derp = populatedMap.getValues("Derp");
		assertEquals(2, derp.size());
		assertEquals(24, derp.get(0));
		assertEquals(5, derp.get(1));
	}
	
	@Test
	public void testReplaceEntireKeyListEmpty(){
		List<String> oldKeys = emptyMap.replaceEntireKeyList(5, Collections.singletonList("Derp"));
		assertNull(oldKeys);
		assertTrue(emptyMap.isEmpty());
		assertEquals(0, emptyMap.keySetSize());
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testReplaceEntireValueList(){
		List<Integer> oldValues = populatedMap.replaceEntireValueList("Test", Collections.singletonList(13));
		assertEquals(2, oldValues.size());
		assertEquals(5, oldValues.get(0));
		assertEquals(82, oldValues.get(1));
		assertTrue(populatedMap.containsKey("Test"));
		assertFalse(populatedMap.containsValue(82));
		assertTrue(populatedMap.containsValue(5));
		List<Integer> test = populatedMap.getValues("Test");
		assertEquals(1, test.size());
		assertEquals(13, test.get(0));
		List<String> test5 = populatedMap.getKeys(5);
		assertEquals(1, test5.size());
		assertEquals("Plop", test5.get(0));
		List<String> test13 = populatedMap.getKeys(13);
		assertEquals(1, test13.size());
		assertEquals("Test", test13.get(0));
	}
	
	@Test
	public void testReplaceEntireValueListEmpty(){
		List<Integer> oldValues = emptyMap.replaceEntireValueList("Test", Collections.singletonList(13));
		assertNull(oldValues);
		assertTrue(emptyMap.isEmpty());
		assertEquals(0, emptyMap.keySetSize());
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testReplaceKey(){
		assertTrue(populatedMap.replaceKey(5, "Plop", "Test2"));
		assertFalse(populatedMap.containsKey("Plop"));
		assertTrue(populatedMap.containsKey("Test2"));
		assertTrue(populatedMap.containsValue(5));
		List<Integer> test2 = populatedMap.getValues("Test2");
		assertEquals(1, test2.size());
		assertEquals(5, test2.get(0));
		List<String> test5 = populatedMap.getKeys(5);
		assertEquals(2, test5.size());
		assertEquals("Test", test5.get(0));
		assertEquals("Test2", test5.get(1));
	}
	
	@Test
	public void testReplaceKeyEmpty(){
		assertFalse(emptyMap.replaceKey(5, "Plop", "Test2"));
		assertTrue(emptyMap.isEmpty());
		assertEquals(0, emptyMap.keySetSize());
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testReplaceValue(){
		assertTrue(populatedMap.replaceValue("Derp", 24, 13));
		assertFalse(populatedMap.containsValue(24));
		assertTrue(populatedMap.containsValue(13));
		assertTrue(populatedMap.containsKey("Derp"));
		List<Integer> derp = populatedMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(13, derp.get(0));
		List<String> test13 = populatedMap.getKeys(13);
		assertEquals(1, test13.size());
		assertEquals("Derp", test13.get(0));
	}
	
	@Test
	public void testReplaceValueEmpty(){
		assertFalse(emptyMap.replaceValue("Derp", 24, 13));
		assertTrue(emptyMap.isEmpty());
		assertEquals(0, emptyMap.keySetSize());
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testReplaceEntireKeyListMatching(){
		assertTrue(populatedMap.replaceEntireKeyList(24, Collections.singletonList("Derp"), Collections.singletonList("Testy")));
		assertFalse(populatedMap.containsKey("Derp"));
		assertTrue(populatedMap.containsKey("Testy"));
		assertTrue(populatedMap.containsValue(24));
		List<Integer> testy = populatedMap.getValues("Testy");
		assertEquals(1, testy.size());
		assertEquals(24, testy.get(0));
		List<String> test24 = populatedMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Testy", test24.get(0));
	}
	
	@Test
	public void testReplaceEntireKeyListNonMatching(){
		assertFalse(populatedMap.replaceEntireKeyList(24, Collections.singletonList("Test"), Collections.singletonList("Testy")));
		assertFalse(populatedMap.containsKey("Testy"));
		assertTrue(populatedMap.containsKey("Test"));
		assertTrue(populatedMap.containsValue(24));
		List<String> test24 = populatedMap.getKeys(24);
		assertEquals(1, test24.size());
		assertEquals("Derp", test24.get(0));
	}
	
	@Test
	public void testReplaceEntireKeyListEmptyMatching(){
		assertFalse(emptyMap.replaceEntireKeyList(24, Collections.singletonList("Derp"), Collections.singletonList("Testy")));
		assertTrue(emptyMap.isEmpty());
		assertEquals(0, emptyMap.keySetSize());
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testReplaceEntireValueListMatching(){
		assertTrue(populatedMap.replaceEntireValueList("Derp", Collections.singletonList(24), Collections.singletonList(13)));
		assertTrue(populatedMap.containsKey("Derp"));
		assertFalse(populatedMap.containsValue(24));
		assertTrue(populatedMap.containsValue(13));
		List<Integer> derp = populatedMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(13, derp.get(0));
		List<String> test13 = populatedMap.getKeys(13);
		assertEquals(1, test13.size());
		assertEquals("Derp", test13.get(0));
	}
	
	@Test
	public void testReplaceEntireValueListNonMatching(){
		assertFalse(populatedMap.replaceEntireValueList("Derp", Collections.singletonList(100), Collections.singletonList(13)));
		assertTrue(populatedMap.containsKey("Derp"));
		assertTrue(populatedMap.containsValue(24));
		assertFalse(populatedMap.containsValue(13));
		List<Integer> derp = populatedMap.getValues("Derp");
		assertEquals(1, derp.size());
		assertEquals(24, derp.get(0));
	}
	
	@Test
	public void testReplaceEntireValueListEmptyMatching(){
		assertFalse(emptyMap.replaceEntireValueList("Derp", Collections.singletonList(24), Collections.singletonList(13)));
		assertTrue(emptyMap.isEmpty());
		assertEquals(0, emptyMap.keySetSize());
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testKeySetSize(){
		emptyMap.put("Derp", 5);
		emptyMap.put("Test", 15);
		emptyMap.put("Test", 5);
		emptyMap.put("Derp", 25);
		assertEquals(2, emptyMap.keySetSize());
	}
	
	@Test
	public void testKeySetSizeEmpty(){
		assertEquals(0, emptyMap.keySetSize());
	}
	
	@Test
	public void testValueSetSize(){
		emptyMap.put("Derp", 5);
		emptyMap.put("Test", 15);
		emptyMap.put("Test", 5);
		emptyMap.put("Testy", 15);
		assertEquals(2, emptyMap.valueSetSize());
	}
	
	@Test
	public void testValueSetSizeEmpty(){
		assertEquals(0, emptyMap.valueSetSize());
	}
	
	@Test
	public void testSize(){
		emptyMap.put("Derp", 5);
		emptyMap.put("Test", 15);
		emptyMap.put("Test", 5);
		emptyMap.put("Testy", 15);
		assertEquals(4, emptyMap.size());
	}
	
	@Test
	public void testSizeEmpty(){
		assertEquals(0, emptyMap.size());
	}
	
	@Test
	public void testClear(){
		populatedMap.clear();
		assertTrue(populatedMap.isEmpty());
		assertEquals(0, populatedMap.keySetSize());
		assertEquals(0, populatedMap.valueSetSize());
		assertEquals(0, populatedMap.size());
	}
}
