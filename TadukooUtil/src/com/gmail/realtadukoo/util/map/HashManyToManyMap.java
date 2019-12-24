package com.gmail.realtadukoo.util.map;

import java.util.Map;

public class HashManyToManyMap<K, V> extends ManyToManyMap<K, V>{
	
	/**
	 * Creates a new HashManyToManyMap where the backing HashMultiMap is constructed with 
	 * the default initial capacity (16) and the default load factor (0.75).
	 */
	public HashManyToManyMap(){
		super(new HashMultiMap<K, V>(), 
				new HashMultiMap<V, K>());
	}
	
	/**
	 * Creates a new HashManyToManyMap where the backing HashMultiMaps are constructed with 
	 * the specified initial capacity and the default load factor (0.75).
	 * 
	 * @param initialCapacity The initial capacity of the backing HashMultiMaps
	 */
	public HashManyToManyMap(int initialCapacity){
		super(new HashMultiMap<K, V>(initialCapacity), 
				new HashMultiMap<V, K>(initialCapacity));
	}
	
	/**
	 * Creates a new HashManyToManyMap where the backing HashMultiMaps are constructed with 
	 * the specified initial capacity and load factor.
	 * 
	 * @param initialCapacity The initial capacity of the backing HashMultiMaps
	 * @param loadFactor The load factor of the backing HashMultiMaps
	 */
	public HashManyToManyMap(int initialCapacity, float loadFactor){
		super(new HashMultiMap<K, V>(initialCapacity, loadFactor), 
				new HashMultiMap<V, K>(initialCapacity, loadFactor));
	}
	
	/**
	 * Creates a new HashManyToManyMap where the backing HashMultiMaps are constructed with 
	 * the same mappings as the specified Map. The HashMultiMap is created with 
	 * default load factor (0.75) and an initial capacity sufficient to hold 
	 * the mappings in the specified Map.
	 * 
	 * @param map The Map whose mappings are to be placed in the backing HashMultiMaps
	 */
	public HashManyToManyMap(Map<K, V> map){
		super(new HashMultiMap<K, V>(), new HashMultiMap<V, K>(), map);
	}
	
	/**
	 * Creates a new HashManyToManyMap where the backing HashMultiMap is constructed with 
	 * the default initial capacity (16) and the default load factor (0.75).
	 * <br>
	 * The ManyToManyMap is then populated with the values present in the specified 
	 * MultiMap.
	 * 
	 * @param multiMap The MultiMap whose mappings are to be placed in this ManyToManyMap
	 */
	public HashManyToManyMap(MultiMap<K, V> multiMap){
		super(new HashMultiMap<K, V>(), new HashMultiMap<V, K>(), multiMap);
	}
	
	/**
	 * Creates a new HashManyToManyMap where the backing HashMultiMap is constructed with 
	 * the default initial capacity (16) and the default load factor (0.75).
	 * <br>
	 * The ManyToManyMap is then populated with the values present in the specified 
	 * ManyToManyMap.
	 * 
	 * @param manyToMAnyMap The ManyToManyMap whose mappings are to be placed in this ManyToManyMap
	 */
	public HashManyToManyMap(ManyToManyMap<K, V> manyToManyMap){
		super(new HashMultiMap<K, V>(), new HashMultiMap<V, K>(), manyToManyMap);
	}
	
	/**
	 * Compares the given object with this HashManyToManyMap for equality. 
	 * Returns true if the given object is also a HashManyToManyMap and the two 
	 * HashManyToManyMaps represent the same mappings.
	 * If they're both HashManyToManyMaps, it will run {@link ManyToManyMap#equals} to compare them.
	 * 
	 * @param o The object to be compared for equality with this HashManyToManyMap
	 * @return true if the given object is equivalent to this HashManyToManyMap
	 */
	public boolean equals(Object o){
		if(o instanceof HashManyToManyMap){
			return super.equals(o);
		}
		return false;
	}
}
