package com.github.tadukoo.util.pojo;

import java.util.Map;
import java.util.Set;

/**
 * Represents a Pojo that holds its values in a Map.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public interface MappedPojo{
	
	/**
	 * Checks whether the pojo has the given key
	 *
	 * @param key The key to look for
	 * @return If the pojo contains the key or not
	 */
	boolean hasKey(String key);
	
	/**
	 * @return The {@link Set} of keys in the pojo
	 */
	Set<String> getKeys();
	
	/**
	 * Checks whether the pojo has an item for the given key
	 *
	 * @param key The key to look for
	 * @return Whether the pojo has an item for the given key
	 */
	boolean hasItem(String key);
	
	/**
	 * Grabs the item contained in the pojo with the given key
	 *
	 * @param key The key of the item to grab
	 * @return The item the pojo has for the given key
	 */
	Object getItem(String key);
	
	/**
	 * Sets the item in the pojo for the given key
	 *
	 * @param key The key of the item to be set
	 * @param item The item to set on the pojo
	 */
	void setItem(String key, Object item);
	
	/**
	 * Removes the item in the pojo for the given key
	 *
	 * @param key The key of the item to be removed
	 */
	void removeItem(String key);
	
	/**
	 * @return The full Map of items in the pojo
	 */
	Map<String, Object> getMap();
}
