package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Util functions for dealing with {@link Map}s.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public final class MapUtil{
	
	// Not allowed to make a MapUtil
	private MapUtil(){ }
	
	/**
	 * Checks if the given Map is blank (either null or an empty map).
	 *
	 * @param map The Map to check
	 * @return true if the Map is null or empty
	 */
	public static boolean isBlank(Map<?, ?> map){
		return map == null || map.isEmpty();
	}
	
	/**
	 * Checks if the given Map is NOT blank (blank = either null or an empty map).
	 *
	 * @param map The Map to check
	 * @return true if the Map is not null and not empty
	 */
	public static boolean isNotBlank(Map<?, ?> map){
		return !isBlank(map);
	}
	
	/**
	 * Creates a Map (specifically a {@link HashMap}) populated with the given entries.
	 *
	 * @param entries Key-Value {@link Pair}s to be put in the Map
	 * @param <K> The key type of the map
	 * @param <V> The value type of the map
	 * @return A newly created map populated with the given entries
	 */
	@SafeVarargs
	public static <K, V> Map<K, V> createMap(Pair<K, V>... entries){
		Map<K, V> map = new HashMap<>();
		for(Pair<K, V> entry: entries){
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}
}
