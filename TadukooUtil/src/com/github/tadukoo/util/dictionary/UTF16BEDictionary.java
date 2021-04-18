package com.github.tadukoo.util.dictionary;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Basic {@link Dictionary} class that supports the {@link StandardCharsets#UTF_16BE UTF-16BE} Charset.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 */
public class UTF16BEDictionary extends AbstractDictionary{
	
	/**
	 * Constructs a new UTF-16BE Dictionary that's empty
	 */
	public UTF16BEDictionary(){
		super();
	}
	
	/**
	 * Constructs a new UTF-16BE Dictionary that contains the words in the given Map
	 *
	 * @param map The Map containing valid words for this Dictionary
	 */
	public UTF16BEDictionary(Map<Character, Map<Character, ?>> map){
		super(map);
	}
	
	/** {@inheritDoc} */
	@Override
	public Charset supportedCharset(){
		return StandardCharsets.UTF_16BE;
	}
}
