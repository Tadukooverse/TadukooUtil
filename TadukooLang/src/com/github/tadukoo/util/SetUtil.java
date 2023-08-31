package com.github.tadukoo.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
	 * @param <T> The {@link Set Set's} type argument and type of variables passed in
	 * @param t The array of variables passed in
	 * @return A {@link Set} containing the given variables
	 */
	@SafeVarargs
	public static <T> Set<T> createSet(T ... t){
		return createSet(false, t);
	}
	
	/**
	 * Creates a {@link Set} using the given array of arguments that maintains its order of elements.
	 *
	 * @param <T> The {@link Set Set's} type argument and type of variables passed in
	 * @param t The array of variables passed in
	 * @return A {@link Set} containing the given variables with order maintained
	 */
	@SafeVarargs
	public static <T> Set<T> createOrderedSet(T ... t){
		return createSet(true, t);
	}
	
	/**
	 * Creates a {@link Set} using the given array of arguments that can maintain order of elements or not.
	 * <br><br>
	 * Note: This is marked as private because apparently if it's not, it becomes ambiguous with the other
	 * createSet method when you call e.g. {@code SetUtil.createSet(true, 5, 1, 2)}, because of java weirdness
	 * I guess. I don't fully know the reason, but I've made this private for that reason. You can just use the
	 * other createSet and createOrderedSet to get the functionality of this anyway.
	 *
	 * @param <T> The {@link Set Set's} type argument and type of variables passed in
	 * @param ordered Whether to maintain the order of the elements in the {@link Set} or not
	 * @param t The array of variables passed in
	 * @return A {@link Set} containing the given variables (with order maintained if specified)
	 */
	@SafeVarargs
	private static <T> Set<T> createSet(boolean ordered, T ... t){
		Set<T> set = ordered?new LinkedHashSet<>():new HashSet<>();
		set.addAll(Arrays.asList(t));
		return set;
	}
	
	/**
	 * Merges two or more {@link Set Sets} without maintaining order of the elements in the sets
	 *
	 * @param <T> The type argument of the {@link Set sets}
	 * @param sets The {@link Set sets} to be merged
	 * @return A {@link Set} containing the contents of the passed in {@link Set sets}
	 */
	@SafeVarargs
	public static <T> Set<T> mergeSets(Set<T> ... sets){
		return mergeSets(false, sets);
	}
	
	/**
	 * Merges two or more {@link Set sets} and maintains order of the elements in them
	 *
	 * @param <T> The type argument of the {@link Set sets}
	 * @param sets The {@link Set sets} to be merged
	 * @return A {@link Set} containing the contents of the passed in {@link Set sets} with order maintained
	 */
	@SafeVarargs
	public static <T> Set<T> mergeOrderedSets(Set<T> ... sets){
		return mergeSets(true, sets);
	}
	
	/**
	 * Merges two or more {@link Set sets} and may maintain order of the elements in them if specified
	 *
	 * @param <T> The type argument of the {@link Set sets}
	 * @param ordered Whether or not to maintain order of the elements in the {@link Set sets}
	 * @param sets The {@link Set sets} to be merged
	 * @return A {@link Set} containing the contents of the passed in {@link Set sets}
	 * (with order maintained if specified)
	 */
	@SafeVarargs
	public static <T> Set<T> mergeSets(boolean ordered, Set<T> ... sets){
		Set<T> resultSet = ordered?new LinkedHashSet<>():new HashSet<>();
		for(Set<T> set: sets){
			resultSet.addAll(set);
		}
		return resultSet;
	}
}
