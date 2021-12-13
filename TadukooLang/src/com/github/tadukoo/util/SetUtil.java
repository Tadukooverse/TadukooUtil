package com.github.tadukoo.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Util functions for dealing with {@link Set Sets}
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3
 */
public class SetUtil{
	
	/** Not allowed to create a SetUtil */
	private SetUtil(){ }
	
	/**
	 * Checks if the given {@link Set} is blank (either null or an empty set).
	 *
	 * @param set The {@link Set} to check
	 * @return true if the {@link Set} is null or empty
	 */
	public static boolean isBlank(Set<?> set){
		return CollectionUtil.isBlank(set);
	}
	
	/**
	 * Checks if the given {@link Set} is NOT blank (blank = either null or an empty set).
	 *
	 * @param set The {@link Set} to check
	 * @return true if the {@link Set} is not null and not empty
	 */
	public static boolean isNotBlank(Set<?> set){
		return CollectionUtil.isNotBlank(set);
	}
	
	/**
	 * Creates a {@link Set} using the given array of arguments.
	 *
	 * @param <T> The {@link Set's} type argument and type of variables passed in
	 * @param t The array of variables passed in
	 * @return A {@link Set} containing the given variables
	 */
	@SafeVarargs
	public static <T> Set<T> createSet(T ... t){
		return new HashSet<>(Arrays.asList(t));
	}
}
