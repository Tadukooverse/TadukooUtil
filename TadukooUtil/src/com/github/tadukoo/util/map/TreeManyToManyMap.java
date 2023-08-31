package com.github.tadukoo.util.map;

import com.github.tadukoo.util.tuple.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A ManyToManyMap class that uses {@link TreeMultiMap} as the backing {@link MultiMap} class.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 * @since Alpha v.0.1
 */
public class TreeManyToManyMap<K, V> extends ManyToManyMap<K, V>{
	
	/**
	 * Creates a new HashManyToManyMap where the backing TreeManyToManyMap uses the natural ordering
	 * of its keys.
	 */
	public TreeManyToManyMap(){
		super(new TreeMultiMap<>(), new TreeMultiMap<>());
	}
	
	/**
	 * Creates a new TreeManyToManyMap where the backing {@link TreeMultiMap} uses the given
	 * {@link Comparator}s to determine the ordering of its keys and values.
	 *
	 * @param keyComparator The Comparator to use in ordering the keys
	 * @param valueComparator The Comparator to use in ordering the values
	 */
	public TreeManyToManyMap(Comparator<? super K> keyComparator, Comparator<? super V> valueComparator){
		super(new TreeMultiMap<>(keyComparator),
				new TreeMultiMap<>(valueComparator));
	}
	
	/**
	 * Creates a new TreeManyToManyMap where the given Pairs are loaded into the
	 * map right away.
	 *
	 * @param entries A collection of key-value Pairs to be put in this ManyToManyMap
	 */
	@SafeVarargs
	public TreeManyToManyMap(Pair<K, V>... entries){
		super(new TreeMultiMap<>(), new TreeMultiMap<>(), entries);
	}
	
	/**
	 * Creates a new TreeManyToManyMap where the backing {@link TreeMultiMap} is constructed with
	 * the same mappings as the specified {@link Map}. The backing TreeMultiMap uses the
	 * natural ordering of its keys.
	 *
	 * @param map The Map whose mappings are to be placed in the backing TreeMultiMap
	 */
	public TreeManyToManyMap(Map<K, V> map){
		super(new TreeMultiMap<>(), new TreeMultiMap<>(), map);
	}
	
	/**
	 * Creates a new TreeManyToManyMap where the backing {@link TreeMultiMap} is constructed with
	 * the same mappings as the specified {@link SortedMap}. The backing TreeMultiMap also
	 * uses the {@link Comparator} used by the given SortedMap.
	 *
	 * @param map The SortedMap whose mappings are to be placed in the backing TreeMultiMap
	 */
	public TreeManyToManyMap(SortedMap<K, V> map){
		super(new TreeMultiMap<>(map.comparator()),
				new TreeMultiMap<>(), map);
	}
	
	/**
	 * Creates a new TreeManyToManyMap where the backing {@link TreeMultiMap} uses the
	 * given {@link Comparator} to determine the ordering of its keys.
	 * <br>
	 * The MultiMap is then populated with the values present in the specified
	 * {@link MultiMap}.
	 *
	 * @param multiMap The MultiMap whose mappings are to be placed in this MultiMap
	 */
	public TreeManyToManyMap(MultiMap<K, V> multiMap){
		super(new TreeMultiMap<>(), new TreeMultiMap<>(), multiMap);
	}
	
	/**
	 * Creates a new TreeManyToManyMap where the backing TreeMultiMap
	 *  uses the natural ordering of its keys.
	 * <br>
	 * The ManyToManyMap is then populated with the values present in the specified
	 * ManyToManyMap.
	 *
	 * @param manyToManyMap The ManyToManyMap whose mappings are to be placed in this ManyToManyMap
	 */
	public TreeManyToManyMap(ManyToManyMap<K, V> manyToManyMap){
		super(new TreeMultiMap<>(), new TreeMultiMap<>(), manyToManyMap);
	}
	
	/**
	 * Compares the given object with this TreeManyToManyMap for equality.
	 * Returns true if the given object is also a TreeManyToManyMap and the two
	 * TreeManyToManyMaps represent the same mappings.
	 * If they're both TreeManyToManyMaps, it will run {@link ManyToManyMap#equals} to compare them.
	 *
	 * @param o The object to be compared for equality with this TreeManyToManyMap
	 * @return true if the given object is equivalent to this TreeManyToManyMap
	 */
	public boolean equals(Object o){
		if(o instanceof TreeManyToManyMap){
			return super.equals(o);
		}
		return false;
	}
	
	/**
	 * @return The keys {@link Comparator} used by the keysToValues TreeMultiMap
	 */
	public Comparator<? super K> keyComparator(){
		return asKeysToValuesMultiMap().comparator();
	}
	
	/**
	 * @return The values {@link Comparator} used by the valuesToKeys TreeMultiMap
	 */
	public Comparator<? super V> valueComparator(){
		return asValuesToKeysMultiMap().comparator();
	}
	
	/**
	 * Returns the underlying TreeMultiMap used for keys to values.
	 *
	 * @return The underlying TreeMultiMap of keys to values
	 */
	public TreeMultiMap<K, V> asKeysToValuesMultiMap(){
		return (TreeMultiMap<K, V>) keysToValues();
	}
	
	/**
	 * Returns the underlying TreeMultiMap used for values to keys.
	 *
	 * @return The underlying TreeMultiMap of values to keys
	 */
	public TreeMultiMap<V, K> asValuesToKeysMultiMap(){
		return (TreeMultiMap<V, K>) valuesToKeys();
	}
	
	/**
	 * @return The first (lowest) key in this manyToManyMap
	 */
	public K firstKey(){
		return asKeysToValuesMultiMap().firstKey();
	}
	
	/**
	 * @return The last (highest) key in this manyToManyMap
	 */
	public K lastKey(){
		return asKeysToValuesMultiMap().lastKey();
	}
	
	/**
	 * @return A key-value mapping associated with the least key in this
	 * manyToManyMap, or null if the manyToManyMap is empty
	 */
	public Map.Entry<K, List<V>> firstEntry(){
		return asKeysToValuesMultiMap().firstEntry();
	}
	
	/**
	 * @return A key-value mapping associated with the greatest key in
	 * this map, or null if the manyToManyMap is empty
	 */
	public Map.Entry<K, List<V>> lastEntry(){
		return asKeysToValuesMultiMap().lastEntry();
	}
	
	/**
	 * Removes and returns a key-value mapping associated with the least
	 * key in this manyToManyMap, or null if the manyToManyMap is empty.
	 *
	 * @return The removed first entry of this manyToManyMap, or null if it's empty
	 */
	public Map.Entry<K, List<V>> pollFirstEntry(){
		return asKeysToValuesMultiMap().pollFirstEntry();
	}
	
	/**
	 * Removes and returns a key-value mapping associated with the greatest
	 * key in this manyToManyMap, or null if the manyToManyMap is empty.
	 *
	 * @return The removed last entry of this manyToManyMap, or null if it's empty
	 */
	public Map.Entry<K, List<V>> pollLastEntry(){
		return asKeysToValuesMultiMap().pollLastEntry();
	}
	
	/**
	 * Returns the greatest key strictly less than the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The greatest key less than key, or null if there is no such key
	 */
	public K lowerKey(K key){
		return asKeysToValuesMultiMap().lowerKey(key);
	}
	
	/**
	 * Returns the least key strictly greater than the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The least key greater than key, or null if there is no such key
	 */
	public K higherKey(K key){
		return asKeysToValuesMultiMap().higherKey(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the greatest key
	 * strictly less than the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return An entry with the greatest key less than key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> lowerEntry(K key){
		return asKeysToValuesMultiMap().lowerEntry(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the least key
	 * strictly greater than the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return An entry with the least key greater than key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> higherEntry(K key){
		return asKeysToValuesMultiMap().higherEntry(key);
	}
	
	/**
	 * Returns the greatest key less than or equal to the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The greatest key less than or equal to key, or null if there is no such key
	 */
	public K floorKey(K key){
		return asKeysToValuesMultiMap().floorKey(key);
	}
	
	/**
	 * Returns the least key greater than or equal to the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return The least key greater than or equal to key, or null if there is no such key
	 */
	public K ceilingKey(K key){
		return asKeysToValuesMultiMap().ceilingKey(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the greatest key
	 * less than or equal to the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return An entry with the greatest key less than or equal to key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> floorEntry(K key){
		return asKeysToValuesMultiMap().floorEntry(key);
	}
	
	/**
	 * Returns a key-value mapping associated with the least key
	 * greater than or equal to the given key, or null if there is no such key.
	 *
	 * @param key The key
	 * @return An entry with the least key greater than or equal to key, or null if there is no such key
	 */
	public Map.Entry<K, List<V>> ceilingEntry(K key){
		return asKeysToValuesMultiMap().ceilingEntry(key);
	}
	
	/**
	 * @return A navigable set view of the keys in this manyToManyMap
	 * @see TreeMap#navigableKeySet()
	 */
	public NavigableSet<K> navigableKeySet(){
		return asKeysToValuesMultiMap().navigableKeySet();
	}
	
	/**
	 * @return A reverse order navigable set view of the keys in this manyToManyMap
	 * @see TreeMap#descendingKeySet()
	 */
	public NavigableSet<K> descendingKeySet(){
		return asKeysToValuesMultiMap().descendingKeySet();
	}
	
	/**
	 * @return R reverse order view of this manyToManyMap
	 * @see TreeMap#descendingMap()
	 */
	public NavigableMap<K, List<V>> descendingMap(){
		return asKeysToValuesMultiMap().descendingMap();
	}
	
	/**
	 * @param fromKey low end point (inclusive) of the keys in the returned map
	 * @param toKey high end point (exclusive) of the keys in the returned map
	 * @return A view of the portion of this manyToManyMap whose keys range from fromKey, inclusive, to toKey, exclusive
	 * @see TreeMap#subMap(Object, Object)
	 */
	public SortedMap<K, List<V>> subMap(K fromKey, K toKey){
		return asKeysToValuesMultiMap().subMap(fromKey, toKey);
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
		return asKeysToValuesMultiMap().subMap(fromKey, fromInclusive, toKey, toInclusive);
	}
	
	/**
	 * @param toKey high end point (exclusive) of the keys in the returned map
	 * @return A view of the portion of this manyToManyMap whose keys are strictly less than toKey
	 * @see TreeMap#headMap(Object)
	 */
	public SortedMap<K, List<V>> headMap(K toKey){
		return asKeysToValuesMultiMap().headMap(toKey);
	}
	
	/**
	 * @param toKey high end point of the keys in the returned map
	 * @param inclusive true if the high end point is to be included in the returned view
	 * @return A view of the portion of this manyToManyMap whose keys are less than (or equal to, if inclusive is true) toKey
	 * @see TreeMap#headMap(Object, boolean)
	 */
	public NavigableMap<K, List<V>> headMap(K toKey, boolean inclusive){
		return asKeysToValuesMultiMap().headMap(toKey, inclusive);
	}
	
	/**
	 * @param fromKey low end point (inclusive) of the keys in the returned map
	 * @return A view of the portion of this manyToManyMap whose keys are greater than or equal to fromKey
	 * @see TreeMap#tailMap(Object)
	 */
	public SortedMap<K, List<V>> tailMap(K fromKey){
		return asKeysToValuesMultiMap().tailMap(fromKey);
	}
	
	/**
	 * @param fromKey low end point of the keys in the returned map
	 * @param inclusive true if the low end point is to be included in the returned view
	 * @return A view of the portion of this manyToManyMap whose keys are greater than (or equal to, if inclusive is true) fromKey
	 * @see TreeMap#tailMap(Object, boolean)
	 */
	public NavigableMap<K, List<V>> tailMap(K fromKey, boolean inclusive){
		return asKeysToValuesMultiMap().tailMap(fromKey, inclusive);
	}
}
