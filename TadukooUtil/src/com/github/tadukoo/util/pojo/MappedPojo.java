package com.github.tadukoo.util.pojo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a Pojo that holds its values in a Map.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.5
 * @since Alpha v.0.2
 */
public interface MappedPojo{
	
	/**
	 * Checks whether the pojo has the given key
	 *
	 * @param key The key to look for
	 * @return If the pojo contains the key or not
	 */
	default boolean hasKey(String key){
		return getMap().containsKey(key);
	}
	
	/**
	 * @return The {@link Set} of keys in the pojo
	 */
	default Set<String> getKeys(){
		return getMap().keySet();
	}
	
	/**
	 * Checks whether the pojo has an item for the given key
	 *
	 * @param key The key to look for
	 * @return Whether the pojo has an item for the given key
	 */
	default boolean hasItem(String key){
		return getMap().get(key) != null;
	}
	
	/**
	 * Grabs the item contained in the pojo with the given key
	 *
	 * @param key The key of the item to grab
	 * @return The item the pojo has for the given key
	 */
	default Object getItem(String key){
		return getMap().get(key);
	}
	
	/**
	 * Sets the item in the pojo for the given key
	 *
	 * @param key The key of the item to be set
	 * @param item The item to set on the pojo
	 */
	default void setItem(String key, Object item){
		getMap().put(key, item);
	}
	
	/**
	 * Removes the item in the pojo for the given key
	 *
	 * @param key The key of the item to be removed
	 */
	default void removeItem(String key){
		getMap().remove(key);
	}
	
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
	default <T extends MappedPojo> T getPojoItem(String key, Class<T> clazz)
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
	
	/**
	 * Helper method to cast an item being stored in this Mapped Pojo as a proper List easily.
	 * The Mapped Pojo class specified for the List must have a constructor that accepts a Mapped Pojo.
	 *
	 * @param key The key of the item to grab
	 * @param clazz The MappedPojo class to be used in the List
	 * @param <T> The class of the pojos in the List
	 * @return The item as a proper List instance, or null
	 * @throws NoSuchMethodException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 * @throws IllegalAccessException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 * @throws InvocationTargetException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 * @throws InstantiationException See {@link java.lang.reflect.Constructor#newInstance(Object...)}
	 */
	@SuppressWarnings("unchecked")
	default <T extends MappedPojo> List<T> getListItem(String key, Class<T> clazz)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		// Grab the table
		List<MappedPojo> table = (List<MappedPojo>) getItem(key);
		
		// If it's null, just return null
		if(table == null){
			return null;
		}
		
		// Create a corrected Table
		List<T> fixedTable = new ArrayList<>();
		for(MappedPojo pojo: table){
			// If the pojo is already an instance of the proper class, just add it to the fixed table
			if(clazz.isInstance(pojo)){
				fixedTable.add((T) pojo);
			}else{
				// If it's not the proper class, correct it
				fixedTable.add(clazz.getDeclaredConstructor(MappedPojo.class).newInstance(pojo));
			}
		}
		
		// Store the fixed table for future use and return it
		setItem(key, fixedTable);
		return fixedTable;
	}
}
