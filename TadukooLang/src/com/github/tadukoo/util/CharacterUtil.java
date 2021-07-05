package com.github.tadukoo.util;

/**
 * Util functions for dealing with Characters.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3.1
 */
public final class CharacterUtil{
	
	/** Not allowed to create a CharacterUtil */
	private CharacterUtil(){ }
	
	/**
	 * @param c The character to be checked
	 * @return true if the given character is a capital letter, false otherwise
	 */
	public static boolean isUpperCase(char c){
		return c >= 'A' && c <= 'Z';
	}
	
	/**
	 * @param c The character to be checked
	 * @return true if the given character is a lowercase letter, false otherwise
	 */
	public static boolean isLowerCase(char c){
		return c >= 'a' && c <= 'z';
	}
	
	/**
	 * @param c The character to be checked
	 * @return true if the given character is a letter, false otherwise
	 */
	public static boolean isLetter(char c){
		return isUpperCase(c) || isLowerCase(c);
	}
	
	/**
	 * @param c The character to be checked
	 * @return true if the given character is a number, false otherwise
	 */
	public static boolean isNumber(char c){
		return c >= '0' && c <= '9';
	}
	
	/**
	 * @param c The character to be capitalized
	 * @return The capital version of the given character, or the character itself if it's not a letter
	 */
	public static char toUpperCase(char c){
		if(isLowerCase(c)){
			return (char) (c - 32);
		}else{
			return c;
		}
	}
	
	/**
	 * @param c The character to change to lowercase
	 * @return The lowercase version of the given character, or the character itself if it's not a letter
	 */
	public static char toLowerCase(char c){
		if(isUpperCase(c)){
			return (char) (c + 32);
		}else{
			return c;
		}
	}
}
