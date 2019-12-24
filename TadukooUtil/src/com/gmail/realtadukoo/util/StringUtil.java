package com.gmail.realtadukoo.util;

import java.util.Collection;

/**
 * Util functions for dealing with or building Strings.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
public class StringUtil{
	
	/**
	 * Builds a string from the given collection of Strings with the given separator placed 
	 * between them.
	 * <br><br>
	 * e.g. buildStringWithSeparator(Arrays.asList("foo", "bar", "baz"), " space ") would 
	 * produce:
	 * <br>
	 * "foo space bar space baz"
	 * 
	 * @param items The collection of strings to put together
	 * @param separator The character to put in-between the strings
	 * @return A String of the given collection with the separator between each of them
	 */
	public static String buildStringWithSeparator(Collection<String> items, String separator){
		// Start the string
		String fullString = "";
		
		// Add each string and the separator after it
		for(String item: items){
			fullString += item + separator;
		}
		
		// Remove the last separator from the end of the string
		return fullString.substring(0, fullString.length()-separator.length());
	}
	
	/**
	 * Builds a string from the given collection of Strings where each is on a newline.
	 * Uses {@link #buildStringWithSeparator} to accomplish this.
	 * <br><br>
	 * e.g. buildStringWithNewLines(Arrays.asList("foo", "bar", "baz") would produce:
	 * <br>
	 * "foo<br>
	 * bar<br>
	 * baz"
	 * 
	 * @param lines The lines to make into a single string
	 * @return A String of the given lines each on a newline
	 */
	public static String buildStringWithNewLines(Collection<String> lines){
		return buildStringWithSeparator(lines, "\n");
	}
}
