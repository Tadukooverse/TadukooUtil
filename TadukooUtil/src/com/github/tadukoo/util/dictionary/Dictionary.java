package com.github.tadukoo.util.dictionary;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Dictionary is used to store and retrieve a collection of valid words.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 */
public interface Dictionary{
	
	/**
	 * @return The {@link Charset} that this Dictionary supports
	 */
	Charset supportedCharset();
	
	/**
	 * @return The valid words for this Dictionary in a Map
	 */
	Map<Character, Map<Character, ?>> asMap();
	
	/**
	 * @param word The word to look for in this Dictionary
	 * @return If the given word is in this Dictionary or not
	 */
	boolean isValidWord(CharSequence word);
	
	/**
	 * Adds the given word to this Dictionary
	 *
	 * @param word The word to be added to this Dictionary
	 * @return If the word was added to the Dictionary (or already exists in it)
	 */
	boolean addWord(CharSequence word);
	
	/**
	 * Removes the given word from this Dictionary
	 *
	 * @param word The word to be removed from this Dictionary
	 * @return true if the word was removed, or false if it wasn't found
	 */
	boolean removeWord(CharSequence word);
}
