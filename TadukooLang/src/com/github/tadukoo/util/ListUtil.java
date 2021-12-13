package com.github.tadukoo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Util functions for dealing with {@link List}s.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.5.2
 * @since Alpha v.0.1
 */
public final class ListUtil{
	
	/** Not allowed to create a ListUtil */
	private ListUtil(){ }
	
	/**
	 * Checks if the given {@link List} is blank (either null or an empty list).
	 *
	 * @param list The {@link List} to check
	 * @return true if the {@link List} is null or empty
	 */
	public static boolean isBlank(List<?> list){
		return CollectionUtil.isBlank(list);
	}
	
	/**
	 * Checks if the given {@link List} is NOT blank (blank = either null or an empty List).
	 *
	 * @param list The {@link List} to check
	 * @return true if the {@link List} is not null and not empty
	 */
	public static boolean isNotBlank(List<?> list){
		return CollectionUtil.isNotBlank(list);
	}
	
	/**
	 * Creates a {@link List} using the given array of arguments. 
	 * This is an improvement over {@link Arrays#asList} as 
	 * it actually allows you to manipulate this {@link List} 
	 * instead of having an immutable List.
	 * 
	 * @param <T> The {@link List List's} type argument and type of variables passed in
	 * @param t The array of variables passed in
	 * @return A {@link List} containing the given variables
	 */
	@SafeVarargs
	public static <T> List<T> createList(T ... t){
		return new ArrayList<>(Arrays.asList(t));
	}
}
