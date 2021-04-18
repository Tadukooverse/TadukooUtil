package com.github.tadukoo.util.dictionary;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Basic {@link Dictionary} class that supports the {@link StandardCharsets#US_ASCII US_ASCII} Charset.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 */
public class US_ASCIIDictionary extends AbstractDictionary{
	
	/**
	 * Constructs a new US_ASCII Dictionary that's empty
	 */
	public US_ASCIIDictionary(){
		super();
	}
	
	/**
	 * Constructs a new US_ASCII Dictionary that contains the words in the given Map
	 *
	 * @param map The Map containing valid words for this Dictionary
	 */
	public US_ASCIIDictionary(Map<Character, Map<Character, ?>> map){
		super(map);
	}
	
	/** {@inheritDoc} */
	@Override
	public Charset supportedCharset(){
		return StandardCharsets.US_ASCII;
	}
}
