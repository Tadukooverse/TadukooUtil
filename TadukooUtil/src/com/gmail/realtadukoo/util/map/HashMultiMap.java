package com.gmail.realtadukoo.util.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A MultiMap class that uses {@link HashMap} as the backing {@link Map} class.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 */
public class HashMultiMap<K, V> extends MultiMap<K, V>{
	
	/**
	 * Creates a new HashMultiMap where the backing HashMap is constructed with 
	 * the default initial capacity (16) and the default load factor (0.75).
	 */
	public HashMultiMap(){
		super(new HashMap<>());
	}
	
	/**
	 * Creates a new HashMultiMap where the backing HashMap is constructed with 
	 * the specified initial capacity and the default load factor (0.75).
	 * 
	 * @param initialCapacity The initial capacity of the backing HashMap
	 */
	public HashMultiMap(int initialCapacity){
		super(new HashMap<>(initialCapacity));
	}
	
	/**
	 * Creates a new HashMultiMap where the backing HashMap is constructed with 
	 * the specified initial capacity and load factor.
	 * 
	 * @param initialCapacity The initial capacity of the backing HashMap
	 * @param loadFactor The load factor of the backing HashMap
	 */
	public HashMultiMap(int initialCapacity, float loadFactor){
		super(new HashMap<>(initialCapacity, loadFactor));
	}
	
	/**
	 * Creates a new HashMultiMap where the backing HashMap is constructed with 
	 * the same mappings as the specified Map. The HashMap is created with 
	 * default load factor (0.75) and an initial capacity sufficient to hold 
	 * the mappings in the specified Map.
	 * 
	 * @param map The Map whose mappings are to be placed in the backing HashMap
	 */
	public HashMultiMap(Map<K, V> map){
		super(new HashMap<>(), map);
	}
	
	/**
	 * Creates a new HashMultiMap where the backing HashMap is constructed with 
	 * the default initial capacity (16) and the default load factor (0.75).
	 * <br>
	 * The MultiMap is then populated with the values present in the specified 
	 * MultiMap.
	 * 
	 * @param multiMap The MultiMap whose mappings are to be placed in this MultiMap
	 */
	public HashMultiMap(MultiMap<K, V> multiMap){
		super(new HashMap<>(), multiMap);
	}
	
	/**
	 * Compares the given object with this HashMultiMap for equality. 
	 * Returns true if the given object is also a HashMultiMap and the two HashMultiMaps represent 
	 * the same mappings.
	 * If they're both HashMultiMaps, it will run {@link MultiMap#equals} to compare them.
	 * 
	 * @param o The object to be compared for equality with this HashMultiMap
	 * @return true if the given object is equivalent to this HashMultiMap
	 */
	public boolean equals(Object o){
		if(o instanceof HashMultiMap){
			return super.equals(o);
		}
		return false;
	}
	
	/**
	 * Returns the underlying {@link HashMap} of this HashMultiMap.
	 * 
	 * @return The underlying HashMap
	 */
	public HashMap<K, List<V>> asMap(){
		return (HashMap<K, List<V>>) super.asMap();
	}
}
