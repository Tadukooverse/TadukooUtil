package com.github.tadukoo.util.dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract implementation of the {@link Dictionary} interface.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 */
public abstract class AbstractDictionary implements Dictionary{
	/** The Map containing the valid words for this Dictionary */
	private final Map<Character, Map<Character, ?>> map;
	
	/**
	 * Constructs a new Dictionary that's empty
	 */
	protected AbstractDictionary(){
		map = new HashMap<>();
	}
	
	/**
	 * Constructs a new Dictionary that contains the words in the given Map
	 *
	 * @param map The Map containing valid words for this Dictionary
	 */
	protected AbstractDictionary(Map<Character, Map<Character, ?>> map){
		this.map = map;
	}
	
	/** {@inheritDoc} */
	@Override
	public Map<Character, Map<Character, ?>> asMap(){
		return map;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean isValidWord(CharSequence word){
		// Check each character is in the map as we go
		Map<?, ?> map = this.map;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(map.containsKey(c)){
				map = (Map<?, ?>) map.get(c);
			}else{
				// If the character's missing, the word isn't there
				return false;
			}
		}
		// Check for the ending null mapping that asserts we've ended a word
		return map.containsKey(null) && map.get(null) == null;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean addWord(CharSequence word){
		// Check that the characters in the word are supported in our Charset
		if(supportedCharset().newEncoder().canEncode(word)){
			// Add each character to the map as we go
			Map<Character, Map<Character, ?>> map = this.map;
			for(int i = 0; i < word.length(); i++){
				char c = word.charAt(i);
				if(map.containsKey(c)){
					//noinspection unchecked
					map = (Map<Character, Map<Character, ?>>) map.get(c);
				}else{
					Map<Character, Map<Character, ?>> newMap = new HashMap<>();
					map.put(c, newMap);
					map = newMap;
				}
			}
			// Add the ending null mapping that asserts we're ending a word
			if(!(map.containsKey(null) && map.get(null) == null)){
				map.put(null, null);
			}
			return true;
		}else{
			// Word can't be added if our Charset doesn't support it
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean removeWord(CharSequence word){
		// Check each character is in the map as we go
		Map<?, ?> map = this.map;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(map.containsKey(c)){
				map = (Map<?, ?>) map.get(c);
			}else{
				// If the character's missing, the word isn't there
				return false;
			}
		}
		// Check for the ending null mapping that asserts we've ended a word
		if(map.containsKey(null) && map.get(null) == null){
			map.remove(null);
			return true;
		}else{
			return false;
		}
	}
}
