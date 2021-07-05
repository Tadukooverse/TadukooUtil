package com.github.tadukoo.util.map;

/**
 * Util functions for dealing with {@link MultiMap}s.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public final class MultiMapUtil{
	
	/** Not allowed to make a MultiMapUtil */
	private MultiMapUtil(){ }
	
	/**
	 * Checks if the given MultiMap is blank (either null or an empty multiMap).
	 *
	 * @param map The MultiMap to check
	 * @return true if the MultiMap is null or empty
	 */
	public static boolean isBlank(MultiMap<?, ?> map){
		return map == null || map.isEmpty();
	}
	
	/**
	 * Checks if the given MultiMap is NOT blank (blank = either null or an empty multiMap).
	 *
	 * @param map The MultiMap to check
	 * @return true if the MultiMap is not null and not empty
	 */
	public static boolean isNotBlank(MultiMap<?, ?> map){
		return !isBlank(map);
	}
}
