package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;

import java.util.*;

/**
 * A MultiMap class that uses {@link TreeMap} as the backing {@link Map} class.
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 * @since Alpha v.0.1
 */
public class TreeMultiMap<K, V> extends MultiMap<K, V>{
	
	/**
	 * Creates a new TreeMultiMap where the backing {@link TreeMap} uses the natural ordering 
	 * of its keys.
	 */
	public TreeMultiMap(){
		super(new TreeMap<>());
	}
	
	/**
	 * Creates a new TreeMultiMap where the backing {@link TreeMap} uses the given 
	 * {@link Comparator} to determine the ordering of its keys.
	 * 
	 * @param comparator The Comparator to use in ordering the keys
	 */
	public TreeMultiMap(Comparator<? super K> comparator){
		super(new TreeMap<>(comparator));
	}
	
	/**
	 * Creates a new TreeMultiMap where the given Pairs are loaded into the
	 * map right away.
	 *
	 * @param entries The Pairs to be placed in this MultiMap
	 */
	@SafeVarargs
	public TreeMultiMap(Pair<K, V>... entries){
		super(new TreeMap<>(), entries);
	}
	
	/**
	 * Creates a new TreeMultiMap where the backing {@link TreeMap} is constructed with 
	 * the same mappings as the specified {@link Map}. The backing TreeMap uses the 
	 * natural ordering of its keys.
	 * 
	 * @param map The Map whose mappings are to be placed in the backing TreeMap
	 */
	public TreeMultiMap(Map<K, V> map){
		super(new TreeMap<>(), map);
	}
	
	/**
	 * Creates a new TreeMultiMap where the backing {@link TreeMap} is constructed with 
	 * the same mappings as the specified {@link SortedMap}. The backing TreeMap also 
	 * uses the {@link Comparator} used by the given SortedMap.
	 * 
	 * @param map The SortedMap whose mappings are to be placed in the backing TreeMap
	 */
	public TreeMultiMap(SortedMap<K, V> map){
		super(new TreeMap<>(map.comparator()), map);
	}
	
	/**
	 * Creates a new TreeMultiMap where the backing {@link TreeMap} uses the 
	 * given {@link Comparator} to determine the ordering of its keys.
	 * <br>
	 * The MultiMap is then populated with the values present in the specified 
	 * {@link MultiMap}.
	 * 
	 * @param multiMap The MultiMap whose mappings are to be placed in this MultiMap
	 */
	public TreeMultiMap(MultiMap<K, V> multiMap){
		super(new TreeMap<>(), multiMap);
	}
	
	/**
	 * Creates a new TreeMultiMap where the backing {@link TreeMap} uses the 
	 * {@link Comparator} present in the given TreeMultiMap to determine the 
	 * ordering of its keys.
	 * <br>
	 * The MultiMap is then populated with the values present in the specified 
	 * TreeMultiMap.
	 * 
	 * @param treeMultiMap The TreeMultiMap whose mappings are to be placed in this MultiMap
	 */
	public TreeMultiMap(TreeMultiMap<K, V> treeMultiMap){
		super(new TreeMap<>(treeMultiMap.comparator()), treeMultiMap);
	}
	
	/**
	 * Returns the underlying {@link TreeMap} of this TreeMultiMap.
	 * 
	 * @return The underlying TreeMap
	 */
	public TreeMap<K, List<V>> asMap(){
		return (TreeMap<K, List<V>>) super.asMap();
	}
	
	/**
	 * @return The {@link Comparator} used by this TreeMultiMap
	 */
	public Comparator<? super K> comparator(){
		return asMap().comparator();
	}
	
	/**
	 * @return The first (lowest) key in this multiMap
	 */
	public K firstKey(){
		return asMap().firstKey();
	}
	
	/**
	 * @return The last (highest) key in this multiMap
	 */
	public K lastKey(){
		return asMap().lastKey();
	}
	
	/**
	 * @return A key-value mapping associated with the least key in this 
	 * multiMap, or null if the multiMap is empty
	 */
	public Map.Entry<K, List<V>> firstEntry(){
		return asMap().firstEntry();
	}
	
	/**
	 * @return A key-value mapping associated with the greatest key in 
	 * this map, or null if the multiMap is empty
	 */
	public Map.Entry<K, List<V>> lastEntry(){
		return asMap().lastEntry();
	}
	
	/**
	 * Removes and returns a key-value mapping associated with the least 
	 * key in this multiMap, or null if the multiMap is empty.
	 * 
	 * @return The removed first entry of this multiMap, or null if it's empty
	 */
	public Map.Entry<K, List<V>> pollFirstEntry(){
		return asMap().pollFirstEntry();
	}
	
	/**
	 * Removes and returns a key-value mapping associated with the greatest 
	 * key in this multiMap, or null if the multiMap is empty.
	 * 
	 * @return The removed last entry of this multiMap, or null if it's empty
	 */
	public Map.Entry<K, List<V>> pollLastEntry(){
		return asMap().pollLastEntry();
	}
	
	/**
	 * Returns the greatest key strictly less than the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The greatest key less than key, or null if there is no such key
	 */
	public K lowerKey(K key){
		return asMap().lowerKey(key);
	}
	
	/**
	 * Returns the least key strictly greater than the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The least key greater than key, or null if there is no such key
	 */
	public K higherKey(K key){
		return asMap().higherKey(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the greatest key 
	 * strictly less than the given key, or null if there is no such key.
	 * 
	 * @param key The key
	 * @return An entry with the greatest key less than key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> lowerEntry(K key){
		return asMap().lowerEntry(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the least key 
	 * strictly greater than the given key, or null if there is no such key.
	 * 
	 * @param key The key
	 * @return An entry with the least key greater than key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> higherEntry(K key){
		return asMap().higherEntry(key);
	}
	
	/**
	 * Returns the greatest key less than or equal to the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The greatest key less than or equal to key, or null if there is no such key
	 */
	public K floorKey(K key){
		return asMap().floorKey(key);
	}
	
	/**
	 * Returns the least key greater than or equal to the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The least key greater than or equal to key, or null if there is no such key
	 */
	public K ceilingKey(K key){
		return asMap().ceilingKey(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the greatest key 
	 * less than or equal to the given key, or null if there is no such key.
	 * 
	 * @param key The key
	 * @return An entry with the greatest key less than or equal to key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> floorEntry(K key){
		return asMap().floorEntry(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the least key 
	 * greater than or equal to the given key, or null if there is no such key.
	 * 
	 * @param key The key
	 * @return An entry with the least key greater than or equal to key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> ceilingEntry(K key){
		return asMap().ceilingEntry(key);
	}
	
	/**
	 * @return A navigable set view of the keys in this multiMap
	 * @see TreeMap#navigableKeySet()
	 */
	public NavigableSet<K> navigableKeySet(){
		return asMap().navigableKeySet();
	}
	
	/**
	 * @return A reverse order navigable set view of the keys in this multiMap
	 * @see TreeMap#descendingKeySet()
	 */
	public NavigableSet<K> descendingKeySet(){
		return asMap().descendingKeySet();
	}
	
	/**
	 * @return A reverse order view of this multiMap
	 * @see TreeMap#descendingMap()
	 */
	public NavigableMap<K, List<V>> descendingMap(){
		return asMap().descendingMap();
	}
	
	/**
	 * @param fromKey low end point (inclusive) of the keys in the returned map
	 * @param toKey high end point (exclusive) of the keys in the returned map
	 * @return A view of the portion of this multiMap whose keys range from fromKey, inclusive, to toKey, exclusive
	 * @see TreeMap#subMap(Object, Object)
	 */
	public SortedMap<K, List<V>> subMap(K fromKey, K toKey){
		return asMap().subMap(fromKey, toKey);
	}
	
	/**
	 * @param fromKey low end point of the keys in the returned map
	 * @param fromInclusive true if the low end point is to be included in the returned view
	 * @param toKey high end point of the keys in the returned map
	 * @param toInclusive true if the high end point is to be included in the returned view
	 * @return A view of the portion of this map whose keys range from fromKey to toKey
	 * @see TreeMap#subMap(Object, boolean, Object, boolean)
	 */
	public NavigableMap<K, List<V>> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive){
		return asMap().subMap(fromKey, fromInclusive, toKey, toInclusive);
	}
	
	/**
	 * @param toKey high end point (exclusive) of the keys in the returned map
	 * @return A view of the portion of this multiMap whose keys are strictly less than toKey
	 * @see TreeMap#headMap(Object)
	 */
	public SortedMap<K, List<V>> headMap(K toKey){
		return asMap().headMap(toKey);
	}
	
	/**
	 * @param toKey high end point of the keys in the returned map
	 * @param inclusive true if the high end point is to be included in the returned view
	 * @return A view of the portion of this multiMap whose keys are less than (or equal to, if inclusive is true) toKey
	 * @see TreeMap#headMap(Object, boolean)
	 */
	public NavigableMap<K, List<V>> headMap(K toKey, boolean inclusive){
		return asMap().headMap(toKey, inclusive);
	}
	
	/**
	 * @param fromKey low end point (inclusive) of the keys in the returned map
	 * @return A view of the portion of this multiMap whose keys are greater than or equal to fromKey
	 * @see TreeMap#tailMap(Object)
	 */
	public SortedMap<K, List<V>> tailMap(K fromKey){
		return asMap().tailMap(fromKey);
	}
	
	/**
	 * @param fromKey low end point of the keys in the returned map
	 * @param inclusive true if the low end point is to be included in the returned view
	 * @return A view of the portion of this multiMap whose keys are greater than (or equal to, if inclusive is true) fromKey
	 * @see TreeMap#tailMap(Object, boolean)
	 */
	public NavigableMap<K, List<V>> tailMap(K fromKey, boolean inclusive){
		return asMap().tailMap(fromKey, inclusive);
	}
}
