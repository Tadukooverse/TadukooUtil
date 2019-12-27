package com.gmail.realtadukoo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Util functions for dealing with {@link List}s.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class ListUtil{
	
	// Not allowed to create a ListUtil
	private ListUtil(){ }
	
	/**
	 * Creates a {@link List} using the given array of arguments. 
	 * This is an improvement over {@link Arrays#asList} as 
	 * it actually allows you to manipulate this {@link List} 
	 * instead of having an immutable List.
	 * 
	 * @param <T> The List's type argument and type of variables passed in
	 * @param t The array of variables passed in
	 * @return A List containing the given variables
	 */
	@SafeVarargs
	public static <T> List<T> createList(T ... t){
		return new ArrayList<>(Arrays.asList(t));
	}
}
