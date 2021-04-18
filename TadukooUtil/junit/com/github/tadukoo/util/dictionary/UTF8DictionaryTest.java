package com.github.tadukoo.util.dictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UTF8DictionaryTest{
	private Dictionary dictionary;
	
	@BeforeEach
	public void setup(){
		dictionary = new UTF8Dictionary();
	}
	
	@Test
	public void testSupportedCharset(){
		assertEquals(StandardCharsets.UTF_8, dictionary.supportedCharset());
	}
	
	@Test
	public void testAsMapEmpty(){
		Map<Character, Map<Character, ?>> map = dictionary.asMap();
		assertNotNull(map);
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void testMapConstructor(){
		Map<Character, Map<Character, ?>> map = new HashMap<>();
		map.put('a', null);
		dictionary = new UTF8Dictionary(map);
		assertEquals(map, dictionary.asMap());
	}
	
	@Test
	public void testIsValidWordEmpty(){
		assertFalse(dictionary.isValidWord("nothing"));
	}
	
	@Test
	public void testAddWord(){
		assertTrue(dictionary.addWord("something"));
		assertTrue(dictionary.isValidWord("something"));
		Map<Character, Map<Character, ?>> map = dictionary.asMap();
		assertNotNull(map);
		assertFalse(map.isEmpty());
	}
	
	@Test
	public void testAddWordAlreadyExists(){
		assertTrue(dictionary.addWord("something"));
		assertTrue(dictionary.isValidWord("something"));
		assertTrue(dictionary.addWord("something"));
	}
	
	@Test
	public void testRemoveWordEmpty(){
		assertFalse(dictionary.isValidWord("nothing"));
		assertFalse(dictionary.removeWord("nothing"));
		assertFalse(dictionary.isValidWord("nothing"));
	}
	
	@Test
	public void testRemoveWord(){
		assertTrue(dictionary.addWord("something"));
		assertTrue(dictionary.isValidWord("something"));
		assertTrue(dictionary.removeWord("something"));
		assertFalse(dictionary.isValidWord("something"));
	}
	
	@Test
	public void testRemoveWordAlreadyRemoved(){
		assertTrue(dictionary.addWord("something"));
		assertTrue(dictionary.isValidWord("something"));
		assertTrue(dictionary.removeWord("something"));
		assertFalse(dictionary.isValidWord("something"));
		assertFalse(dictionary.removeWord("something"));
		assertFalse(dictionary.isValidWord("something"));
	}
}
