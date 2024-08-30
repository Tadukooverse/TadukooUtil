package com.github.tadukoo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util functions for dealing with Strings, including building and parsing them.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3.1
 * @since Pre-Alpha
 */
public final class StringUtil{
	
	/** Not allowed to create a StringUtil */
	private StringUtil(){ }
	
	/**
	 * Checks if the given string is blank (either null or the empty string).
	 * 
	 * @param text The string to check
	 * @return true if the string is null or the empty string
	 */
	public static boolean isBlank(String text){
		return text == null || text.isEmpty();
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
	 * Checks if all the given strings are blank or not (blank = either null or the empty string).
	 *
	 * @param texts The strings to check
	 * @return true if all strings are blank (null/empty string), false otherwise
	 */
	public static boolean allBlank(String ... texts){
		// If any string is not blank, return false
		for(String text: texts){
			if(isNotBlank(text)){
				return false;
			}
		}
		// Return true if we didn't hit a false
		return true;
	}
	
	/**
	 * Checks if all the given strings are not blank (blank = either null or the empty string).
	 *
	 * @param texts The strings to check
	 * @return true if all strings are not blank (not null/empty string), false otherwise
	 */
	public static boolean noneBlank(String ... texts){
		// If any string is blank, return false
		for(String text: texts){
			if(isBlank(text)){
				return false;
			}
		}
		// Return true if we didn't hit a false
		return true;
	}
	
	/**
	 * Checks if any of the given strings are blank (blank = either null or the empty string).
	 *
	 * @param texts The strings to check
	 * @return true if any string is blank (null/empty string), false otherwise
	 */
	public static boolean anyBlank(String ... texts){
		// If any string is blank, return true
		for(String text: texts){
			if(isBlank(text)){
				return true;
			}
		}
		// Return false if we didn't hit a true
		return false;
	}
	
	/**
	 * Checks if any of the given strings are not blank (blank = either null or the empty string).
	 *
	 * @param texts The strings to check
	 * @return true if any string is not blank (not null/empty string), false otherwise
	 */
	public static boolean anyNotBlank(String ... texts){
		// If any string is not blank, return true
		for(String text: texts){
			if(isNotBlank(text)){
				return true;
			}
		}
		// Return false if we didn't hit a true
		return false;
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
	 * A null-safe trim method. Useful for simplifying a block where you grab a potentially null String from
	 * somewhere and would have to check it's not null before attempting a trim.
	 *
	 * @param text The text to be trimmed
	 * @return {@code null} if text is null, or a trimmed version of text
	 */
	public static String trim(String text){
		if(text == null){
			return null;
		}
		return text.trim();
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
		if(obj instanceof String s){
			return s;
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
	 * Parses the given text into a List using the given regex to split and optionally
	 * trimming to remove whitespace in the resulting strings.
	 *
	 * @param text The text to parse into a List of Strings
	 * @param regex The regex to use in separating the given text
	 * @param trim Whether to trim any whitespace off the resulting strings
	 * @return The resulting List of Strings
	 */
	public static List<String> parseListFromStringWithRegex(String text, String regex, boolean trim){
		List<String> strings = new ArrayList<>();
		
		// If there is no match of the regex, just make a list with the text
		if(!Pattern.compile(regex).matcher(text).find()){
			// Optionally trim the text
			strings.add(trim?text.trim():text);
		}else{
			// Split the text on the regex
			String[] textSplit = text.split(regex);
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
	
	/**
	 * Parses the given String into a List of strings by matching using the given regex
	 *
	 * @param text The text to be parsed into a List of Strings
	 * @param pattern The regular expression to use for matching for parsing the text
	 * @param trim Whether to trim the matching strings or not
	 * @return The List of Strings produced by parsing the text
	 */
	public static List<String> parseListFromStringWithPattern(String text, String pattern, boolean trim){
		List<String> strings = new ArrayList<>();
		
		// Set up the matcher
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		while(matcher.find()){
			strings.add(trim?matcher.group(0).trim():matcher.group(0));
		}
		
		return strings;
	}
	
	/**
	 * Adds a tab to every new line in the given string
	 *
	 * @param text A String to be indented
	 * @return The String with every line indented
	 */
	public static String indentAllLines(String text){
		return "\t" + text.replace("\n", "\n\t");
	}
	
	/*
	 * Handling String Case
	 */
	
	/**
	 * Capitalizes the first letter of the given String (if it's a letter, otherwise just returns the String).
	 * Will properly handle null/empty string by returning what was given
	 *
	 * @param text The text to capitalize the first letter of
	 * @return The resulting text after the first letter is capitalized
	 */
	public static String capitalizeFirstLetter(String text){
		// If string is blank, just return it
		if(StringUtil.isBlank(text)){
			return text;
		}
		
		// Capitalize the first character
		char firstChar = CharacterUtil.toUpperCase(text.charAt(0));
		
		// If the string is just 1 character, return it
		if(text.length() == 1){
			return String.valueOf(firstChar);
		}else{
			return firstChar + text.substring(1);
		}
	}
	
	/**
	 * Checks if the given text is PascalCase or not.
	 * PascalCase has no underscores or spaces between words and every word is capitalized, including
	 * the first word.
	 *
	 * @param text The text to be checked
	 * @return true if the text is PascalCase, false otherwise
	 */
	public static boolean isPascalCase(String text){
		return !text.contains("_") && !text.contains(" ") && CharacterUtil.isUpperCase(text.charAt(0));
	}
	
	/**
	 * Converts the given text to PascalCase.
	 * It removes any spaces and underscores and capitalizes all words in the text.
	 *
	 * @param text The text to be converted
	 * @return The PascalCase version of the given text
	 */
	public static String toPascalCase(String text){
		// If we have spaces, replace them with underscores (in case we have both, to make it easier)
		text = text.replaceAll(" ", "_");
		
		// If we have snake_case, we need to remove the underscores and do some capitalization
		StringBuilder newText;
		if(text.contains("_")){
			String[] pieces = text.split("_");
			newText = new StringBuilder();
			for(String piece: pieces){
				newText.append(CharacterUtil.toUpperCase(piece.charAt(0))).append(piece.substring(1));
			}
		}else{
			// If we don't have snake_case, we only need to make sure the first character is upper case
			newText = new StringBuilder(CharacterUtil.toUpperCase(text.charAt(0)) + text.substring(1));
		}
		
		return newText.toString();
	}
	
	/**
	 * Checks if the given text is camelCase or not.
	 * camelCase has no underscores or spaces between words and every word is capitalized, excluding
	 * the first word.
	 *
	 * @param text The text to be checked
	 * @return true if the text is camelCase, false otherwise
	 */
	public static boolean isCamelCase(String text){
		return !text.contains("_") && !text.contains(" ") && CharacterUtil.isLowerCase(text.charAt(0));
	}
	
	/**
	 * Converts the given text to camelCase.
	 * It removes any spaces and underscores and capitalizes all words in the text, except for the first word,
	 * which is made lowercase.
	 *
	 * @param text The text to be converted
	 * @return The camelCase version of the given text
	 */
	public static String toCamelCase(String text){
		// If we have spaces, replace them with underscores (in case we have both, to make it easier)
		text = text.replaceAll(" ", "_");
		
		// If we have snake_case, we need to remove the underscores and do some capitalization
		String newText = text;
		if(text.contains("_")){
			String[] pieces = text.split("_");
			StringBuilder newTextBuilder = new StringBuilder();
			for(String piece: pieces){
				newTextBuilder.append(CharacterUtil.toUpperCase(piece.charAt(0))).append(piece.substring(1));
			}
			newText = newTextBuilder.toString();
		}
		
		// Change/Ensure the first character is lower case
		newText = CharacterUtil.toLowerCase(newText.charAt(0)) + newText.substring(1);
		
		return newText;
	}
	
	/**
	 * Checks if the given text is snake_case or not.
	 * snake_case has an underscore between words, and there are no spaces.
	 *
	 * @param text The text to be checked
	 * @return true is the text is snake_case, false otherwise
	 */
	public static boolean isSnakeCase(String text){
		return text.contains("_") && !text.contains(" ");
	}
	
	/**
	 * Converts the given text to snake_case.
	 * Any spaces are replaced with underscores, underscores are placed between words, and everything
	 * is made lowercase.
	 *
	 * @param text The text to be converted
	 * @return The snake_case version of the given text
	 */
	public static String toSnakeCase(String text){
		// If we have underscores and no spaces, it's already snake_case
		if(text.contains("_") && !text.contains(" ")){
			return text;
		}else{
			// Insert an underscore any time there's a capital letter and make it lowercase
			StringBuilder newText = new StringBuilder();
			for(char c: text.toCharArray()){
				if(CharacterUtil.isUpperCase(c)){
					// Only append an underscore if the previous character is not an underscore
					if(!newText.toString().isEmpty() && newText.charAt(newText.length() - 1) != '_'){
						newText.append('_');
					}
					newText.append(CharacterUtil.toLowerCase(c));
				}else if(c == ' '){
					// Replace spaces with underscores
					newText.append('_');
				}else{
					newText.append(c);
				}
			}
			return newText.toString();
		}
	}
}
