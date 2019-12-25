package com.gmail.realtadukoo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Util functions for dealing with or building Strings.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 * @since Pre-Alpha
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
	
	/**
	 * Builds a string from the given collection of Strings where each is separated by 
	 * a comma.
	 * Uses {@link #buildStringWithSeparator} to accomplish this.
	 * <br><br>
	 * e.g. buildCommaSeparatedString(Arrays.asList("foo", "bar", "baz") would produce:
	 * <br>
	 * "foo,bar,baz"
	 * 
	 * @param lines The lines to make into a single string
	 * @return A String of the given lines as a comma-separated string
	 */
	public static String buildCommaSeparatedString(Collection<String> lines){
		return buildStringWithSeparator(lines, ",");
	}
	
	/**
	 * Parses the given text into a List using the given separator to split and optionally 
	 * trimming to remove whitespace in the resulting strings.
	 * 
	 * @param text The text to parse into a List of Strings
	 * @param separator The separator String to use in separating the given text
	 * @param trim Whether to trim any whitespace off the resulting strings
	 * @return The resulting List of Strings
	 */
	public static List<String> parseListFromStringWithSeparator(String text, String separator, boolean trim){
		List<String> strings = new ArrayList<>();
		
		// If there is no instance of the separator string, just make a list with the text
		if(!text.contains(separator)){
			// Optionally trim the text
			strings.add(trim?text.trim():text);
		}else{
			// Split the text on the separator string
			String[] textSplit = text.split(separator);
			for(String split: textSplit){
				// Add each string to the list, optionally trimming them
				strings.add(trim?split.trim():split);
			}
		}
		
		return strings;
	}
	
	/**
	 * Parses a comma-separated list into a List of Strings.
	 * 
	 * @param text The text to convert into a List of Strings
	 * @return The List of Strings produced by parsing the text
	 */
	public static List<String> parseCommaSeparatedListFromString(String text){
		return parseListFromStringWithSeparator(text, ",", true);
	}
}
