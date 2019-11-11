package com.gmail.realtadukoo.util.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * This class relates keys to values, but allows for one key to reference multiple values.
 * <br>
 * The implementation of this class is essentially a {@link Map} of keys to {@link ArrayList ArrayLists} of values.
 * <br>
 * For the most part, it's the same methods that a Map has, but there are some unique to this to allow for more specific 
 * functionality.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Pre-Alpha
 * 
 * @param <K> The type of keys in this MultiMap
 * @param <V> The type of values in this MultiMap
 */
public abstract class MultiMap<K, V>{
	/** Underlying Map used to store the key-value pairs */
	private Map<K, List<V>> theMap;
	
	/**
	 * Sets the backing {@link Map} for this MultiMap.
	 * 
	 * @param theMap The Map to use for this MultiMap
	 */
	public MultiMap(Map<K, List<V>> theMap){
		this.theMap = theMap;
	}
	
	/**
	 * Sets the backing {@link Map} for this MultiMap and calls 
	 * {@link #putAll(Map) putAll} for the given otherMap's entries.
	 * 
	 * @param theMap The Map to use for this MultiMap
	 * @param otherMap A Map containing entries to add to this MultiMap
	 */
	public MultiMap(Map<K, List<V>> theMap, Map<K, V> otherMap){
		this.theMap = theMap;
		putAll(otherMap);
	}
	
	/**
	 * Sets the backing {@link Map} for this MultiMap and calls 
	 * {@link #putAll(MultiMap) putAll} for the given multiMap's entries.
	 * 
	 * @param theMap The Map to use for this MultiMap
	 * @param multiMap A MultiMap containing entries to add to this MultiMap
	 */
	public MultiMap(Map<K, List<V>> theMap, MultiMap<K, V> multiMap){
		this.theMap = theMap;
		putAll(multiMap);
	}
	
	/**
	 * Returns true if this map contains no key-value mappings.
	 * Calls {@link Map#isEmpty} on the underlying {@link Map}.
	 * 
	 * @return true if this map contains no key-value mappings.
	 */
	public final boolean isEmpty(){
		return theMap.isEmpty();
	}
	
	/**
	 * Compares the given object with this MultiMap for equality. 
	 * Returns true if the given object is also a MultiMap and the two MultiMaps represent the same mappings.
	 * If they're both MultiMaps, it will run the {@link MultiMap#asMap} method on both MultiMaps and 
	 * call {@link Map#equals} to compare their mappings.
	 * 
	 * @param o The object to be compared for equality with this MultiMap
	 * @return true if the given object is equivalent to this MultiMap
	 */
	public boolean equals(Object o){
		if(o instanceof MultiMap){
			return this.asMap().equals(((MultiMap<?, ?>) o).asMap());
		}
		return false;
	}
	
	/**
	 * Returns true if this MultiMap contains a mapping for the specified key.
	 * Calls {@link Map#containsKey} on the underlying {@link Map} to see if it contains 
	 * the given key or not.
	 * 
	 * @param key The key to check for
	 * @return Whether this MultiMap contains the given key or not
	 */
	public final boolean containsKey(K key){
		return theMap.containsKey(key);
	}
	
	/**
	 * Returns true if this MultiMap maps one or more keys to the specified value.
	 * Calls {@link #values} and checks if the result contains the given value or not.
	 * 
	 * @param value The value to check for
	 * @return Whether this MultiMap contains the given value or not
	 */
	public final boolean containsValue(V value){
		return values().contains(value);
	}
	
	/**
	 * Returns the list of values to which the specified key is mapped, 
	 * or null if this MultiMap contains no mappings for the key.
	 * Calls {@link Map#get} on the underlying {@link Map}.
	 * 
	 * @param key The key whose mapped values are being requested
	 * @return The list of values mapped to the given key
	 */
	public final List<V> get(K key){
		// Grab the list associated with the given key
		return theMap.get(key);
	}
	
	/**
	 * Returns the Set of keys in this MultiMap.
	 * Calls {@link Map#keySet} on the underlying {@link Map}.
	 * 
	 * @return The Set of keys in this MultiMap
	 */
	public final Set<K> keySet(){
		return theMap.keySet();
	}
	
	/**
	 * Returns the list of values in this MultiMap as a single List.
	 * Calls {@link Map#values} on the underlying {@link Map} and streams the Collection of Lists 
	 * to a single List.
	 * 
	 * @return A list of all the values in this MultiMap
	 */
	public final List<V> values(){
		return theMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
	}
	
	/**
	 * Returns the underlying {@link Map} of this MultiMap.
	 * 
	 * @return The underlying Map
	 */
	public final Map<K, List<V>> asMap(){
		return theMap;
	}
	
	/**
	 * Associates the specified value with the specified key in this MultiMap.
	 * Will create a new {@link ArrayList} with the given value if no List 
	 * currently exists for the given key in this MultiMap, or add to the 
	 * existing one if it already exists.
	 * 
	 * @param key The key to associate with the given value
	 * @param value The value to associate with the given key
	 */
	public final void put(K key, V value){
		// Grab the current list from the map (if it exists)
		List<V> list = theMap.get(key);
		
		// If a list doesn't exist for this key, create a new one
		if(list == null){
			list = new ArrayList<V>();
		}
		
		// Add the value to the list
		list.add(value);
		
		// Put the list in the map
		theMap.put(key, list);
	}
	
	/**
	 * Associates all of the given values with the given key.
	 * 
	 * @param key The key to associate with the given values
	 * @param values The values to associate with the given key
	 */
	public final void putAll(K key, List<V> values){
		values.forEach(value -> put(key, value));
	}
	
	/**
	 * Associates all of the key-value mappings from the given Map 
	 * into this MultiMap.
	 * 
	 * @param map The map whose mappings should be added to this
	 */
	public final void putAll(Map<K, V> map){
		map.forEach((key, value) -> put(key, value));
	}
	
	/**
	 * Associates all of the key-value mappings from the given MultiMap 
	 * into this MultiMap.
	 * 
	 * @param map The MulitMap whose mappings should be added to this one
	 */
	public final void putAll(MultiMap<K, V> map){
		map.forEach((key, value) -> put(key, value));
	}
	
	/**
	 * Removes all values associated with the given key from the MultiMap.
	 * Calls {@link Map#remove(Object)} on the underlying {@link Map}.
	 * 
	 * @param key The key whose associations are to be removed
	 * @return The List of values the key used to be associated with
	 */
	public final List<V> remove(K key){
		return theMap.remove(key);
	}
	
	/**
	 * Removes the specified value from being associated with the 
	 * specified key, if it exists.
	 * <br>
	 * Will return whether the mapping existed or not.
	 * <br><br>
	 * If the removed value was the last one associated with the given key, 
	 * this method will remove the empty list from the underlying {@link Map}.
	 * 
	 * @param key The key of the given key-value association to remove
	 * @param value The value of the given key-value association to remove
	 * @return true if there was a mapping of the given key to the given value
	 */
	public final boolean remove(K key, V value){
		// Grab the list of values for the given key
		List<V> values = theMap.get(key);
		
		// If there's no mapping for the given key, return false
		if(values == null){
			return false;
		}
		
		// Remove the value from the list
		boolean existed = values.remove(value);
		
		// If it was the last value, remove the empty list
		if(values.isEmpty()){
			theMap.remove(key);
		}
		
		// Return the result
		return existed;
	}
	
	/**
	 * Removes the given list of values associated with the given key if the 
	 * list of values matches the current list.
	 * <br>
	 * This method is calling {@link Map#remove(Object,Object)} on the underlying {@link Map}.
	 * 
	 * @param key The key of the given key-value association to remove
	 * @param values The values of the given key-value association to remove
	 * @return Whether the list of values was removed or not
	 */
	public final boolean removeEntireList(K key, List<V> values){
		return theMap.remove(key, values);
	}
	
	/**
	 * Replaces the current list of values associated with the given key 
	 * with the given list of values.
	 * <br>
	 * Calls {@link Map#replace(K,V)} on the underlying {@link Map}.
	 * 
	 * @param key The key to change the associations of
	 * @param values The values to associate with the given key
	 * @return The previous list of values associated with the given key
	 */
	public final List<V> replaceList(K key, List<V> values){
		return theMap.replace(key, values);
	}
	
	/**
	 * Replaces the given old value with the given new value for an 
	 * association to the given key, if the old value is currently 
	 * associated with the given key.
	 * <br>
	 * Checks if there is an association between the given key and 
	 * given old value, and if so, replaces it with the given 
	 * new value.
	 * 
	 * @param key The key to change the association of
	 * @param oldValue The old value associated with the given key
	 * @param newValue The new value to associate with the given key
	 * @return true if the value was replaced
	 */
	public final boolean replace(K key, V oldValue, V newValue){
		// Grab the list of current values associated with the given key
		List<V> values = theMap.get(key);
		
		// If the current list is null, return false
		if(values == null){
			return false;
		}
		
		// Check if the list contains the given old value
		if(values.contains(oldValue)){
			// If it does, replace it and return true
			values.remove(oldValue);
			values.add(newValue);
			return true;
		}else{
			// If the list doesn't contain the given old value, return false
			return false;
		}
	}
	
	/**
	 * Replaces the current list of values associated with the given key 
	 * with the given new list of values if the given old list matches the 
	 * current list.
	 * <br>
	 * Calls {@link Map#replace(K,V,V)} on the underlying {@link Map}.
	 * 
	 * @param key The key to change the associations of
	 * @param oldValues The old list of values associated with the given key
	 * @param newValues The values to associate with the given key
	 * @return true if the values were replaced
	 */
	public final boolean replaceEntireList(K key, List<V> oldValues, List<V> newValues){
		return theMap.replace(key, oldValues, newValues);
	}
	
	/**
	 * Returns the number of keys currently in this MultiMap.
	 * <br>
	 * Calls {@link Map#size} on the underlying {@link Map}.
	 * 
	 * @return The number of keys currently in this MultiMap
	 */
	public final int keySetSize(){
		return theMap.size();
	}
	
	/**
	 * Returns the number of key-value associations of this MultiMap.
	 * <br>
	 * Calls {@link #values} to get the full list of values and returns the size of it.
	 * 
	 * @return The number of key-value associations of this MultiMap.
	 */
	public final int size(){
		return values().size();
	}
	
	/**
	 * Performs the given action for each key-value association in this 
	 * MultiMap until all associations have been processed or the 
	 * action throws an exception.
	 * 
	 * @param action The action to be performed for each key-value association
	 */
	public final void forEach(BiConsumer<? super K, ? super V> action){
		for(K key : keySet()){
			for(V value: theMap.get(key)){
				action.accept(key, value);
			}
		}
	}
	
	/**
	 * Removes all of the key-value associations from this MultiMap.
	 * The MultiMap will be empty after this call returns.
	 * <br>
	 * Calls {@link Map#clear} on the underlying {@link Map}.
	 */
	public final void clear(){
		theMap.clear();
	}
}
