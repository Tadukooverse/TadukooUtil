package com.github.tadukoo.util.dictionary;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Basic {@link Dictionary} class that supports the {@link StandardCharsets#UTF_8 UTF-8} Charset.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 */
public class UTF8Dictionary extends AbstractDictionary{
	
	/**
	 * Constructs a new UTF-8 Dictionary that's empty
	 */
	public UTF8Dictionary(){
		super();
	}
	
	/**
	 * Constructs a new UTF-8 Dictionary that contains the words in the given Map
	 *
	 * @param map The Map containing valid words for this Dictionary
	 */
	public UTF8Dictionary(Map<Character, Map<Character, ?>> map){
		super(map);
	}
	
	/** {@inheritDoc} */
	@Override
	public Charset supportedCharset(){
		return StandardCharsets.UTF_8;
	}
}
