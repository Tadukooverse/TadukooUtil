package com.github.tadukoo.util.pojo;

import java.lang.reflect.InvocationTargetException;
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
	
	/**
	 * Helper method to cast an item being stored in this Mapped Pojo as another MappedPojo object easily.
	 * The other Mapped Pojo object must have a constructor that takes in a MappedPojo.
	 *
	 * @param key The key of the item to grab
	 * @param clazz The class to cast the item to
	 * @param <T> The class the item should be cast to
	 * @return The item as a proper instance of the class, or null
	 * @throws NoSuchMethodException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 * @throws IllegalAccessException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 * @throws InvocationTargetException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 * @throws InstantiationException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 */
	@SuppressWarnings("unchecked")
	default <T extends MappedPojo> T getItemAsPojo(String key, Class<T> clazz)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// Grab the item
		Object obj = getItem(key);
		
		// If it's null, just return null
		if(obj == null){
			return null;
		}
		
		// If it's already an instance of the class, cast it and return it
		if(clazz.isInstance(obj)){
			return (T) obj;
		}else{
			// Otherwise, make it a proper instance, store it in the map, and return it
			MappedPojo pojo = (MappedPojo) obj;
			T t = clazz.getDeclaredConstructor(MappedPojo.class).newInstance(pojo);
			setItem(key, t);
			return t;
		}
	}
}
