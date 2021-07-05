package com.github.tadukoo.util.map;

/**
 * Util functions for dealing with {@link ManyToManyMap}s.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public final class ManyToManyMapUtil{
	
	/** Not allowed to make a ManyToManyMapUtil */
	private ManyToManyMapUtil(){ }
	
	/**
	 * Checks if the given ManyToManyMap is blank (either null or an empty manyToManyMap).
	 *
	 * @param map The ManyToManyMap to check
	 * @return true if the ManyToManyMap is null or empty
	 */
	public static boolean isBlank(ManyToManyMap<?, ?> map){
		return map == null || map.isEmpty();
	}
	
	/**
	 * Checks if the given ManyToManyMap is NOT blank (blank = either null or an empty manyToManyMap).
	 *
	 * @param map The ManyToManyMap to check
	 * @return true if the ManyToManyMap is not null and not empty
	 */
	public static boolean isNotBlank(ManyToManyMap<?, ?> map){
		return !isBlank(map);
	}
}
