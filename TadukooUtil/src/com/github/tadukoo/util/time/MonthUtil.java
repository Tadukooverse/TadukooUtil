package com.github.tadukoo.util.time;

import com.github.tadukoo.util.StringUtil;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Month Util provides utilities for dealing with {@link Month}s.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 */
public final class MonthUtil{
	
	// Can't create a MonthUtil
	private MonthUtil(){ }
	
	/**
	 * Parses a {@link Month} from the given string. May return null if none match.
	 *
	 * @param str The string to be parsed into a {@link Month}
	 * @return The {@link Month} matching the given string, or null
	 */
	public static Month parseFromString(String str){
		for(Month month: Month.values()){
			if(StringUtil.equalsIgnoreCase(str, month.toString())){
				return month;
			}
		}
		return null;
	}
	
	/**
	 * Converts the given {@link Month} to its full, capitalized, US-Locale string. This is an
	 * easy way instead of having to call {@link Month#getDisplayName(TextStyle, Locale)} all the time.
	 *
	 * @param month The {@link Month} to get as a string
	 * @return The full string of the {@link Month}
	 */
	public static String asString(Month month){
		return month.getDisplayName(TextStyle.FULL, Locale.US);
	}
	
	/**
	 * Creates a String array of the {@link Month}s.
	 *
	 * @return A String array of the {@link Month}s
	 */
	public static String[] asStringArray(){
		String[] monthStrings = new String[12];
		int i = 0;
		for(Month month: Month.values()){
			monthStrings[i] = asString(month);
			i++;
		}
		return monthStrings;
	}
}
