package com.github.tadukoo.util.dictionary;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Basic {@link Dictionary} class that supports the {@link StandardCharsets#ISO_8859_1 ISO-LATIN-1/ISO_8859_1} Charset.
 *  *
 *  * @author Logan Ferree (Tadukoo)
 *  * @version Alpha v.0.4
 */
public class ISO_LATIN1Dictionary extends AbstractDictionary{
	
	/**
	 * Constructs a new ISO_LATIN_1 Dictionary that's empty
	 */
	public ISO_LATIN1Dictionary(){
		super();
	}
	
	/**
	 * Constructs a new ISO_LATIN_1 Dictionary that contains the words in the given Map
	 *
	 * @param map The Map containing valid words for this Dictionary
	 */
	public ISO_LATIN1Dictionary(Map<Character, Map<Character, ?>> map){
		super(map);
	}
	
	/** {@inheritDoc} */
	@Override
	public Charset supportedCharset(){
		return StandardCharsets.ISO_8859_1;
	}
}
