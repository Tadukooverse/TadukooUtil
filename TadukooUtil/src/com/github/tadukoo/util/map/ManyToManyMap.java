package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * This class relates keys to values, but allows for a many-to-many relationship of keys to values.
 * <br>
 * The implementation of this class is essentially two {@link MultiMap}s. One associates keys to values, 
 * and the other associates values to keys.
 * <br>
 * For the most part, it's the same methods that a Map has, but there are some unique to this to allow 
 * for more specific functionality.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 * @since Pre-Alpha
 * 
 * @param <K> The type of keys in this ManyToManyMap
 * @param <V> The type of values in this ManyToManyMap
 */
public abstract class ManyToManyMap<K, V>{
	/** The backing keys to values {@link MultiMap} */
	private final MultiMap<K, V> keysToValues;
	/** The backing values to keys {@link MultiMap} */
	private final MultiMap<V, K> valuesToKeys;
	
	/**
	 * Sets the backing {@link MultiMap}s for this ManyToManyMap.
	 * 
	 * @param keysToValues The keysToValues MultiMap to use for this ManyToManyMap
	 * @param valuesToKeys The valuesToKeys MultiMap to use for this ManyToManyMap
	 */
	protected ManyToManyMap(MultiMap<K, V> keysToValues, MultiMap<V, K> valuesToKeys){
		this.keysToValues = keysToValues;
		this.valuesToKeys = valuesToKeys;
	}
	
	/**
	 * Sets the backing {@link MultiMap}s for this ManyToManyMap and calls
	 * {@link #putAllKeyValMappings(Pair[])} for the given key-value Pairs.
	 *
	 * @param keysToValues The keysToValues MultiMap to use for this ManyToManyMap
	 * @param valuesToKeys The valuesToKeys MultiMap to use for this ManyToManyMap
	 * @param entries A collection of key-value Pairs to be put in this ManyToManyMap
	 */
	@SafeVarargs
	protected ManyToManyMap(MultiMap<K, V> keysToValues, MultiMap<V, K> valuesToKeys, Pair<K, V>... entries){
		this.keysToValues = keysToValues;
		this.valuesToKeys = valuesToKeys;
		putAllKeyValMappings(entries);
	}
	
	/**
	 * Sets the backing {@link MultiMap}s for this ManyToManyMap and calls 
	 * {@link #putAllKeyValMappings(Map) putAll} for the given map's entries.
	 * 
	 * @param keysToValues The keysToValues MultiMap to use for this ManyToManyMap
	 * @param valuesToKeys The valuesToKeys MultiMap to use for this ManyToManyMap
	 * @param map A Map containing entries to add to this ManyToManyMap
	 */
	protected ManyToManyMap(MultiMap<K, V> keysToValues, MultiMap<V, K> valuesToKeys, Map<K, V> map){
		this.keysToValues = keysToValues;
		this.valuesToKeys = valuesToKeys;
		putAllKeyValMappings(map);
	}
	
	/**
	 * Sets the backing {@link MultiMap}s for this ManyToManyMap and calls 
	 * {@link #putAllKeyValMappings(MultiMap) putAll} for the given MultiMap's entries.
	 * 
	 * @param keysToValues The keysToValues MultiMap to use for this ManyToManyMap
	 * @param valuesToKeys The valuesToKeys MultiMap to use for this ManyToManyMap
	 * @param multiMap A MultiMap containing entries to add to this ManyToManyMap
	 */
	protected ManyToManyMap(MultiMap<K, V> keysToValues, MultiMap<V, K> valuesToKeys,
			MultiMap<K, V> multiMap){
		this.keysToValues = keysToValues;
		this.valuesToKeys = valuesToKeys;
		putAllKeyValMappings(multiMap);
	}
	
	/**
	 * Sets the backing {@link MultiMap}s for this ManyToManyMap and calls 
	 * {@link #putAllKeyValMappings(ManyToManyMap) putAll} for the given ManyToManyMap's entries.
	 * 
	 * @param keysToValues The keysToValues MultiMap to use for this ManyToManyMap
	 * @param valuesToKeys The valuesToKeys MultiMap to use for this ManyToManyMap
	 * @param manyToManyMap A ManyToManyMap containing entries to add to this ManyToManyMap
	 */
	protected ManyToManyMap(MultiMap<K, V> keysToValues, MultiMap<V, K> valuesToKeys,
			ManyToManyMap<K, V> manyToManyMap){
		this.keysToValues = keysToValues;
		this.valuesToKeys = valuesToKeys;
		putAllKeyValMappings(manyToManyMap);
	}
	
	/**
	 * Returns true if this map contains no key-value mappings.
	 * Calls {@link MultiMap#isEmpty} on the underlying MultiMaps.
	 * 
	 * @return true if this map contains no key-value mappings.
	 */
	public final boolean isEmpty(){
		return keysToValues.isEmpty() && valuesToKeys.isEmpty();
	}
	
	/**
	 * Compares the given object with this ManyToManyMap for equality. 
	 * Returns true if the given object is also a ManyToManyMap and their underlying {@link MultiMap MultiMaps}
	 * represent the same mappings.
	 * If they're both ManyToManyMaps, it will run the {@link MultiMap#equals} method on their underlying 
	 * MultiMaps to compare their mappings.
	 * 
	 * @param o The object to be compared for equality with this map
	 * @return true if the given object is equivalent to this ManyToManyMap
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof ManyToManyMap){
			// If it's a ManyToManyMap, compare their underlying MultiMaps
			ManyToManyMap<?, ?> otherMap = (ManyToManyMap<?, ?>) o;
			return this.keysToValues().equals(otherMap.keysToValues()) && 
					this.valuesToKeys().equals(otherMap.valuesToKeys());
		}
		
		return false;
	}
	
	/**
	 * Returns true if this ManyToManyMap contains a mapping for the specified key.
	 * Calls {@link MultiMap#containsKey} on the underlying keysToValues MultiMap to 
	 * see if it contains the given key or not.
	 * 
	 * @param key The key to check for
	 * @return Whether this ManyToManyMap contains the given key or not
	 */
	public final boolean containsKey(K key){
		return keysToValues.containsKey(key);
	}
	
	/**
	 * Returns true if this ManyToManyMap contains a mapping for the specified value.
	 * Calls {@link MultiMap#containsKey} on the underlying valuesToKeys MultiMap to 
	 * see if it contains the given value or not.
	 * 
	 * @param value The value to check for
	 * @return Whether this ManyToManyMap contains the given value or not
	 */
	public final boolean containsValue(V value){
		return valuesToKeys.containsKey(value);
	}
	
	/**
	 * Returns the list of keys to which the specified value is mapped, 
	 * or null if this ManyToManyMap contains no mappings for the value.
	 * Calls {@link MultiMap#get} on the underlying valuesToKeys MultiMap.
	 * 
	 * @param value The value whose mapped keys are being requested
	 * @return The list of keys mapped to the given value
	 */
	public final List<K> getKeys(V value){
		return valuesToKeys.get(value);
	}
	
	/**
	 * Returns the list of values to which the specified key is mapped, 
	 * or null if this ManyToManyMap contains no mappings for the key.
	 * Calls {@link MultiMap#get} on the underlying keysToValues MultiMap.
	 * 
	 * @param key The key whose mapped values are being requested
	 * @return The list of values mapped to the given key
	 */
	public final List<V> getValues(K key){
		return keysToValues.get(key);
	}
	
	/**
	 * Returns the Set of keys in this ManyToManyMap.
	 * Calls {@link MultiMap#keySet} on the underlying keysToValues MultiMap.
	 * 
	 * @return The Set of keys in this ManyToManyMap
	 */
	public final Set<K> keySet(){
		return keysToValues.keySet();
	}
	
	/**
	 * Returns the Set of values in this ManyToManyMap.
	 * Calls {@link MultiMap#keySet} on the underlying valuesToKeys MultiMap.
	 * 
	 * @return The Set of values in this ManyToManyMap
	 */
	public final Set<V> valueSet(){
		return valuesToKeys.keySet();
	}
	
	/**
	 * Returns the underlying keysToValues {@link MultiMap} of this ManyToManyMap.
	 * 
	 * @return The underlying keysToValues MultiMap
	 */
	public final MultiMap<K, V> keysToValues(){
		return keysToValues;
	}
	
	/**
	 * Returns the underlying valuesToKeys {@link MultiMap} of this ManyToManyMap.
	 * 
	 * @return The underlying valuesToKeys MultiMap
	 */
	public final MultiMap<V, K> valuesToKeys(){
		return valuesToKeys;
	}
	
	/**
	 * Associates the specified value with the specified key in this ManyToManyMap.
	 * Will call {@link MultiMap#put} on both underlying MultiMaps.
	 * 
	 * @param key The key to associate with the given value
	 * @param value The value to associate with the given key
	 */
	public final void put(K key, V value){
		keysToValues.put(key, value);
		valuesToKeys.put(value, key);
	}
	
	/**
	 * Associates all of the given keys with the given value.
	 * Calls {@link MultiMap#putAll(Object, List)} on the underlying 
	 * valuesToKeys MultiMap and {@link MultiMap#put} on the underlying 
	 * keysToValues MultiMap for each of the given keys.
	 * 
	 * @param value The value to associate with the given keys
	 * @param keys The keys to associate with the given value
	 */
	public final void putAllKeys(V value, List<K> keys){
		keys.forEach(key -> keysToValues.put(key, value));
		valuesToKeys.putAll(value, keys);
	}
	
	/**
	 * Associates all of the given values with the given key.
	 * Calls {@link MultiMap#putAll(Object, List)} on the underlying 
	 * keysToValues MultiMap and {@link MultiMap#put} on the underlying 
	 * valuesToKeys MultiMap for each of the given values.
	 * 
	 * @param key The key to associate with the given values
	 * @param values The values to associate with the given key
	 */
	public final void putAllValues(K key, List<V> values){
		keysToValues.putAll(key, values);
		values.forEach(value -> valuesToKeys.put(value, key));
	}
	
	/**
	 * Puts all the given key-value Pairs into this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#putAll(Pair[])} on the underlying keysToValues
	 * {@link MultiMap} and {@link MultiMap#put} on the underlying
	 * valuesToKeys MultiMap for each key-value mapping.
	 *
	 * @param entries The key-value Pairs to be put in this ManyToManyMap
	 */
	@SafeVarargs
	public final void putAllKeyValMappings(Pair<K, V> ... entries){
		keysToValues.putAll(entries);
		for(Pair<K, V> entry: entries){
			valuesToKeys.put(entry.getValue(), entry.getKey());
		}
	}
	
	/**
	 * Puts all the given value-key Pairs into this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#putAll(Pair[])} on the underlying valuesToKeys
	 * {@link MultiMap} and {@link MultiMap#put} on the underlying
	 * keysToValues MultiMap for each value-key mapping.
	 *
	 * @param entries The value-key Pairs to be put in this ManyToManyMap
	 */
	@SafeVarargs
	public final void putAllValKeyMappings(Pair<V, K> ... entries){
		valuesToKeys.putAll(entries);
		for(Pair<V, K> entry: entries){
			keysToValues.put(entry.getValue(), entry.getKey());
		}
	}
	
	/**
	 * Associates all of the key-value mappings from the given Map 
	 * into this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#putAll(Map)} on the underlying keysToValues 
	 * {@link MultiMap} and {@link MultiMap#put} on the underlying 
	 * valuesToKeys MultiMap for each key-value mapping.
	 * 
	 * @param map The map whose mappings should be added to this
	 */
	public final void putAllKeyValMappings(Map<K, V> map){
		keysToValues.putAll(map);
		map.forEach((key, value) -> valuesToKeys.put(value, key));
	}
	
	/**
	 * Associates all of the value-key mappings from the given Map 
	 * into this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#putAll(Map)} on the underlying valuesToKeys 
	 * {@link MultiMap} and {@link MultiMap#put} on the underlying 
	 * keysToValues MultiMap for each key-value mapping.
	 * 
	 * @param map The map whose mappings should be added to this
	 */
	public final void putAllValKeyMappings(Map<V, K> map){
		map.forEach((key, value) -> keysToValues.put(value, key));
		valuesToKeys.putAll(map);
	}
	
	/**
	 * Associates all of the key-value mappings from the given MultiMap 
	 * into this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#putAll(Map)} on the underlying keysToValues 
	 * {@link MultiMap} and {@link MultiMap#put} on the underlying 
	 * valuesToKeys MultiMap for each key-value mapping.
	 * 
	 * @param map The MultiMap whose mappings should be added to this
	 */
	public final void putAllKeyValMappings(MultiMap<K, V> map){
		keysToValues.putAll(map);
		map.forEach((key, value) -> valuesToKeys.put(value, key));
	}
	
	/**
	 * Associates all of the value-key mappings from the given MultiMap 
	 * into this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#putAll(Map)} on the underlying valuesToKeys 
	 * {@link MultiMap} and {@link MultiMap#put} on the underlying 
	 * keysToValues MultiMap for each key-value mapping.
	 * 
	 * @param map The MultiMap whose mappings should be added to this
	 */
	public final void putAllValKeyMappings(MultiMap<V, K> map){
		map.forEach((key, value) -> keysToValues.put(value, key));
		valuesToKeys.putAll(map);
	}
	
	/**
	 * Associates all of the key-value mappings from the given ManyToManyMap 
	 * into this ManyToManyMap.
	 * <br>
	 * Calls {@link #put} for each of the key-value mappings of the given 
	 * ManyToManyMap.
	 * 
	 * @param map The ManyToManyMap whose mappings should be added to this
	 */
	public final void putAllKeyValMappings(ManyToManyMap<K, V> map){
		map.forEach(this::put);
	}
	
	/**
	 * Associates all of the value-key mappings from the given ManyToManyMap 
	 * into this ManyToManyMap.
	 * <br>
	 * Calls {@link #put} for each of the value-key mappings of the given 
	 * ManyToManyMap.
	 * 
	 * @param map The ManyToManyMap whose mappings should be added to this
	 */
	public final void putAllValKeyMappings(ManyToManyMap<V, K> map){
		map.forEach((key, value) -> put(value, key));
	}
	
	/**
	 * Removes all values associated with the given key from the ManyToManyMap.
	 * Calls {@link MultiMap#removeKey(Object)} on the underlying keysToValues
	 * MultiMap and then {@link MultiMap#remove(Object, Object)} on the underlying 
	 * valuesToKeys MultiMap for each of the former key-value pairs being removed.
	 * 
	 * @param key The key whose associations are to be removed
	 * @return The List of values the key used to be associated with
	 */
	public final List<V> removeKey(K key){
		List<V> values = keysToValues.removeKey(key);
		if(values != null){
			values.forEach(value -> valuesToKeys.remove(value, key));
		}
		return values;
	}
	
	/**
	 * Removes all keys associated with the given value from the ManyToManyMap.
	 * Calls {@link MultiMap#removeKey(Object)} on the underlying valuesToKeys
	 * MultiMap and then {@link MultiMap#remove(Object, Object)} on the underlying 
	 * keysToValues MultiMap for each of the former key-value pairs being removed.
	 * 
	 * @param value The value whose associations are to be removed
	 * @return The List of keys the value used to be associated with
	 */
	public final List<K> removeValue(V value){
		List<K> keys = valuesToKeys.removeKey(value);
		if(keys != null){
			keys.forEach(key -> keysToValues.remove(key, value));
		}
		return keys;
	}
	
	/**
	 * Removes the specified value from being associated with the 
	 * specified key, if the mapping exists.
	 * <br>
	 * Will return whether the mapping existed or not.
	 * <br><br>
	 * Calls {@link MultiMap#remove(Object, Object)} on the underlying 
	 * keysToValues MultiMap and if that returns true, calls the same 
	 * method on the underlying valuesToKeys MultiMap to remove it entirely.
	 * 
	 * @param key The key of the given key-value association to remove
	 * @param value The value of the given key-value association to remove
	 * @return true if there was a mapping of the given key to the given value
	 */
	public final boolean remove(K key, V value){
		if(keysToValues.remove(key, value)){
			valuesToKeys.remove(value, key);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the given list of keys associated with the given value if the 
	 * list of keys matches the current list associated with that value.
	 * <br>
	 * Calls {@link MultiMap#removeEntireList} on the underlying valuesToKeys
	 * {@link MultiMap}. If that returns true, {@link MultiMap#remove(Object, Object)} 
	 * is then called on the keysToValues MultiMap for each of the key-value mappings.
	 * 
	 * @param value The value of the given value-key association to remove
	 * @param keys The keys of the given value-key association to remove
	 * @return Whether the list of keys was removed or not
	 */
	public final boolean removeEntireKeysList(V value, List<K> keys){
		if(valuesToKeys.removeEntireList(value, keys)){
			keys.forEach(key -> keysToValues.remove(key, value));
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the given list of values associated with the given key if the 
	 * list of values matches the current list associated with that key.
	 * <br>
	 * Calls {@link MultiMap#removeEntireList} on the underlying keysToValues
	 * {@link MultiMap}. If that returns true, {@link MultiMap#remove(Object, Object)} 
	 * is then called on the valuesToKeys MultiMap for each of the key-value mappings.
	 * 
	 * @param key The key of the given key-value association to remove
	 * @param values The values of the given key-value association to remove
	 * @return Whether the list of values was removed or not
	 */
	public final boolean removeEntireValuesList(K key, List<V> values){
		if(keysToValues.removeEntireList(key, values)){
			values.forEach(value -> valuesToKeys.remove(value, key));
			return true;
		}
		return false;
	}
	
	/**
	 * Replaces the current list of keys associated with the given value 
	 * with the given list of keys, only if it is currently mapped to a
	 * value.
	 * <br>
	 * Calls {@link MultiMap#replaceEntireList(Object, List)} on the underlying
	 * valuesToKeys {@link MultiMap} and then 
	 * {@link MultiMap#remove(Object, Object) removes} the old keys from 
	 * the keysToValues MultiMap and {@link MultiMap#put puts} the new 
	 * keys in it.
	 * 
	 * @param value The value to change the associations of
	 * @param keys The keys to associate with the given value
	 * @return The previous list of keys associated with the given value
	 */
	public final List<K> replaceEntireKeyList(V value, List<K> keys){
		List<K> oldKeys = valuesToKeys.replaceEntireList(value, keys);
		if(oldKeys != null){
			oldKeys.forEach(key -> keysToValues.remove(key, value));
			keys.forEach(key -> keysToValues.put(key, value));
		}
		return oldKeys;
	}
	
	/**
	 * Replaces the current list of values associated with the given key 
	 * with the given list of values.
	 * <br>
	 * Calls {@link MultiMap#replaceEntireList(Object, List)} on the underlying
	 * keysToValues {@link MultiMap} and then 
	 * {@link MultiMap#remove(Object, Object) removes} the old values from 
	 * the valuesToKeys MultiMap and {@link MultiMap#put puts} the new 
	 * values in it.
	 * 
	 * @param key The key to change the associations of
	 * @param values The values to associate with the given key
	 * @return The previous list of values associated with the given key
	 */
	public final List<V> replaceEntireValueList(K key, List<V> values){
		List<V> oldValues = keysToValues.replaceEntireList(key, values);
		if(oldValues != null){
			oldValues.forEach(value -> valuesToKeys.remove(value, key));
			values.forEach(value -> valuesToKeys.put(value, key));
		}
		return oldValues;
	}
	
	/**
	 * Replaces the given old key with the given new key for an 
	 * association to the given value, if the old key is currently 
	 * associated with the given value.
	 * <br>
	 * Calls {@link MultiMap#replace} on the underlying valuesToKeys 
	 * {@link MultiMap} and if that returns true, it 
	 * {@link MultiMap#remove(Object, Object) removes} the old key 
	 * from the keysToValues MultiMap and {@link MultiMap#put puts} 
	 * the new key in it at the end of the list.
	 * 
	 * @param value The value to change the association of
	 * @param oldKey The old key associated with the given value
	 * @param newKey The new key to associate with the given value
	 * @return true if the key was replaced
	 */
	public final boolean replaceKey(V value, K oldKey, K newKey){
		if(valuesToKeys.replace(value, oldKey, newKey)){
			keysToValues.remove(oldKey, value);
			keysToValues.put(newKey, value);
			return true;
		}
		return false;
	}
	
	/**
	 * Replaces the given old value with the given new value for an 
	 * association to the given key, if the old value is currently 
	 * associated with the given key.
	 * <br>
	 * Calls {@link MultiMap#replace} on the underlying keysToValues 
	 * {@link MultiMap} and if that returns true, it 
	 * {@link MultiMap#remove(Object, Object) removes} the old value 
	 * from the valuesToKeys MultiMap and {@link MultiMap#put puts} 
	 * the new value in it at the end of the list.
	 * 
	 * @param key The key to change the association of
	 * @param oldValue The old value associated with the given key
	 * @param newValue The new value to associate with the given key
	 * @return true if the value was replaced
	 */
	public final boolean replaceValue(K key, V oldValue, V newValue){
		if(keysToValues.replace(key, oldValue, newValue)){
			valuesToKeys.remove(oldValue, key);
			valuesToKeys.put(newValue, key);
			return true;
		}
		return false;
	}
	
	/**
	 * Replaces the current list of keys associated with the given value 
	 * with the given new list of keys if the given old list matches the 
	 * current list.
	 * <br>
	 * Calls {@link MultiMap#replaceEntireList} on the underlying valuesToKeys
	 * {@link MultiMap} and if that returns true, it then 
	 * {@link MultiMap#remove(Object, Object) removes} the old key-value mappings 
	 * from the keysToValues MultiMap and {@link MultiMap#put puts} the new 
	 * key-value mappings in it.
	 * 
	 * @param value The value to change the associations of
	 * @param oldKeys The old list of keys associated with the given value
	 * @param newKeys The keys to associate with the given value
	 * @return true if the keys were replaced
	 */
	public final boolean replaceEntireKeyList(V value, List<K> oldKeys, List<K> newKeys){
		if(valuesToKeys.replaceEntireList(value, oldKeys, newKeys)){
			oldKeys.forEach(key -> keysToValues.remove(key, value));
			newKeys.forEach(key -> keysToValues.put(key, value));
			return true;
		}
		return false;
	}
	
	/**
	 * Replaces the current list of values associated with the given key 
	 * with the given new list of values if the given old list matches the 
	 * current list.
	 * <br>
	 * Calls {@link MultiMap#replaceEntireList} on the underlying keysToValues
	 * {@link MultiMap} and if that returns true, it then 
	 * {@link MultiMap#remove(Object, Object) removes} the old value-key mappings 
	 * from the valuesToKeys MultiMap and {@link MultiMap#put puts} the new 
	 * value-key mappings in it.
	 * 
	 * @param key The key to change the associations of
	 * @param oldValues The old list of values associated with the given key
	 * @param newValues The values to associate with the given key
	 * @return true if the values were replaced
	 */
	public final boolean replaceEntireValueList(K key, List<V> oldValues, List<V> newValues){
		if(keysToValues.replaceEntireList(key, oldValues, newValues)){
			oldValues.forEach(value -> valuesToKeys.remove(value, key));
			newValues.forEach(value -> valuesToKeys.put(value, key));
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of keys currently in this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#keySetSize} on the underlying keysToValues {@link MultiMap}.
	 * 
	 * @return The number of keys currently in this ManyToManyMap
	 */
	public final int keySetSize(){
		return keysToValues.keySetSize();
	}
	
	/**
	 * Returns the number of values currently in this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#keySetSize} on the underlying valuesToKeys {@link MultiMap}.
	 * 
	 * @return The number of values currently in this ManyToManyMap
	 */
	public final int valueSetSize(){
		return valuesToKeys.keySetSize();
	}
	
	/**
	 * Returns the number of key-value associations of this ManyToManyMap.
	 * <br>
	 * Calls {@link MultiMap#size} on the underlying keysToValues {@link MultiMap}.
	 * 
	 * @return The number of key-value associations of this ManyToManyMap.
	 */
	public final int size(){
		return keysToValues.size();
	}
	
	/**
	 * Performs the given action for each key-value association in this 
	 * ManyToManyMap until all associations have been processed or the 
	 * action throws an exception.
	 * 
	 * @param action The action to be performed for each key-value association
	 */
	public final void forEach(BiConsumer<? super K, ? super V> action){
		for(K key: keySet()){
			for(V value: keysToValues.get(key)){
				action.accept(key, value);
			}
		}
	}
	
	/**
	 * Removes all of the key-value associations from this ManyToManyMap.
	 * The ManyToManyMap will be empty after this call returns.
	 * <br>
	 * Calls {@link MultiMap#clear} on the underlying {@link MultiMap}s.
	 */
	public final void clear(){
		keysToValues.clear();
		valuesToKeys.clear();
	}
}
