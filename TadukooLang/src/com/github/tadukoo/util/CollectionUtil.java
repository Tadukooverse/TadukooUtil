package com.github.tadukoo.util;

import java.util.Collection;

/**
 * Util functions for dealing with {@link Collection Collections}
 *
 * @author Logan Ferree
 * @version Beta v.0.5.2
 */
public class CollectionUtil{
	
	/** Not allowed to create a CollectionUtil */
	private CollectionUtil(){ }
	
	/**
	 * Checks if the given {@link Collection} is blank (either null or an empty collection).
	 *
	 * @param collection The {@link Collection} to check
	 * @return true if the {@link Collection} is null or empty
	 */
	public static boolean isBlank(Collection<?> collection){
		return collection == null || collection.isEmpty();
	}
	
	/**
	 * Checks if the given {@link Collection} is NOT blank (blank = either null or an empty collection).
	 *
	 * @param collection The {@link Collection} to check
	 * @return true if the {@link Collection} is not null and not empty
	 */
	public static boolean isNotBlank(Collection<?> collection){
		return !isBlank(collection);
	}
}
