package com.gmail.realtadukoo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Util functions for dealing with Strings, including building and parsing them.
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 * @since Pre-Alpha
 */
public final class StringUtil{
	
	// Not allowed to create a StringUtil
	private StringUtil(){ }
	
	/**
	 * Checks if the given string is blank (either null or the empty string).
	 * 
	 * @param text The string to check
	 * @return true if the string is null or the empty string
	 */
	public static boolean isBlank(String text){
		return text == null || text.equals("");
	}
	
	/**
	 * Checks if the given string is NOT blank (blank = either null or the empty string).
	 * 
	 * @param text The string to check
	 * @return true if the string is not null and not the empty string
	 */
	public static boolean isNotBlank(String text){
		return !isBlank(text);
	}
	
	/**
	 * Checks if the two strings are equal using String.equals(), but properly 
	 * handles null (if they're both null, returns true, if one is null and the 
	 * other isn't, return false instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected string we want
	 * @return true if actual.equals(expected) or they're both null
	 */
	public static boolean equals(String actual, String expected){
		if(actual == null && expected == null){
			// If both null, return true
			return true;
		}else if(actual == null || expected == null){
			// If one is null, and the other isn't, return false
			return false;
		}else{
			// Otherwise check as normal
			return actual.equals(expected);
		}
	}
	
	/**
	 * Checks if the given string is equal to any of the given expected strings 
	 * using String.equals(), but properly handles null (if the string is null and 
	 * null is in the expected strings, returns true, if the string is null and the 
	 * expected strings don't have null, return false instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected strings we want
	 * @return true if actual.equals({any of the expected}) or they're both null
	 */
	public static boolean equalsAny(String actual, String ... expected){
		if(actual == null){
			// If actual string is null, check for null in the expected
			for(String expect: expected){
				if(expect == null){
					return true;
				}
			}
		}else{
			// If actual string isn't null, check that actual.equals any of the expected
			for(String expect: expected){
				if(actual.equals(expect)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the two strings are equal using String.equalsIgnoreCase(), but properly 
	 * handles null (if they're both null, returns true, if one is null and the 
	 * other isn't, return false instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected string we want
	 * @return true if actual.equalsIgnoreCase(expected) or they're both null
	 */
	public static boolean equalsIgnoreCase(String actual, String expected){
		if(actual == null && expected == null){
			// If both null, return true
			return true;
		}else if(actual == null || expected == null){
			// If one is null and the other isn't, return false
			return false;
		}else{
			// Otherwise check as normal
			return actual.equalsIgnoreCase(expected);
		}
	}
	
	/**
	 * Checks if the given string is equal to any of the given expected strings 
	 * using String.equalsIgnoreCase(), but properly handles null (if the string is null and 
	 * null is in the expected strings, returns true, if the string is null and the 
	 * expected strings don't have null, return false instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected strings we want
	 * @return true if actual.equalsIgnoreCase({any of the expected}) or they're both null
	 */
	public static boolean equalsAnyIgnoreCase(String actual, String ... expected){
		if(actual == null){
			// If actual string is null, check for null in the expected
			for(String expect: expected){
				if(expect == null){
					return true;
				}
			}
		}else{
			// If actual string isn't null, check that actual.equalsIgnoreCase any of the expected
			for(String expect: expected){
				if(actual.equalsIgnoreCase(expect)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Checks if the two strings are NOT equal using String.equals(), but properly 
	 * handles null (if they're both null, returns false, if one is null and the 
	 * other isn't, return true instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected string we want
	 * @return false if actual.equals(expected) or they're both null
	 */
	public static boolean notEquals(String actual, String expected){
		return !equals(actual, expected);
	}
	
	/**
	 * Checks if the given string is NOT equal to any of the given expected strings 
	 * using String.equals(), but properly handles null (if the string is null and 
	 * null is in the expected strings, returns false, if the string is null and the 
	 * expected strings don't have null, return true instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected strings we want
	 * @return false if actual.equals({any of the expected}) or they're both null
	 */
	public static boolean notEqualsAny(String actual, String ... expected){
		return !equalsAny(actual, expected);
	}
	
	/**
	 * Checks if the two strings are NOT equal using String.equalsIgnoreCase(), but properly 
	 * handles null (if they're both null, returns false, if one is null and the 
	 * other isn't, return true instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected string we want
	 * @return false if actual.equalsIgnoreCase(expected) or they're both null
	 */
	public static boolean notEqualsIgnoreCase(String actual, String expected){
		return !equalsIgnoreCase(actual, expected);
	}
	
	/**
	 * Checks if the given string is NOT equal to any of the given expected strings 
	 * using String.equalsIgnoreCase(), but properly handles null (if the string is null and 
	 * null is in the expected strings, returns false, if the string is null and the 
	 * expected strings don't have null, return true instead of throwing an NPE).
	 * 
	 * @param actual The actual string being checked
	 * @param expected The expected strings we want
	 * @return false if actual.equalsIgnoreCase({any of the expected}) or they're both null
	 */
	public static boolean notEqualsAnyIgnoreCase(String actual, String ... expected){
		return !equalsAnyIgnoreCase(actual, expected);
	}
	
	/**
	 * Converts the given Object to a String, including proper
	 * null handling.
	 *
	 * @param obj The Object to convert to a String
	 * @return null if obj is null, or a String representing the Object
	 */
	public static String convertToString(Object obj){
		// If obj is null, just return null
		if(obj == null){
			return null;
		}
		
		// If obj is a String, cast it to a String
		if(obj instanceof String){
			return (String) obj;
		}
		
		// Otherwise just use String.valueOf
		return String.valueOf(obj);
	}
	
	/**
	 * Converts an entire Collection of items to strings, and
	 * returns them as a List.
	 *
	 * @param items The Collection of items to convert to strings
	 * @param <T> The type of the items in the collection
	 * @return A List of the given items converted to Strings
	 */
	public static <T> List<String> convertCollectionToStrings(Collection<T> items){
		List<String> strings = new ArrayList<>();
		for(T item: items){
			strings.add(convertToString(item));
		}
		return strings;
	}
	
	/*
	 * String Building Section
	 */
	
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
		StringBuilder fullString = new StringBuilder();
		
		// Add each string and the separator after it
		for(String item: items){
			fullString.append(item).append(separator);
		}
		
		// Remove the last separator from the end of the string
		return fullString.substring(0, fullString.length()-separator.length());
	}
	
	/**
	 * Builds a string from the given collection of Strings with no
	 * separator between them.
	 *
	 * @param items The strings to combine into a single String
	 * @return A String of all the given items
	 */
	public static String buildString(Collection<String> items){
		return buildStringWithSeparator(items, "");
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
	 * @param items The items to make into a single string
	 * @return A String of the given items as a comma-separated string
	 */
	public static String buildCommaSeparatedString(Collection<String> items){
		return buildStringWithSeparator(items, ",");
	}
	
	/*
	 * String Parsing Section
	 */
	
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
