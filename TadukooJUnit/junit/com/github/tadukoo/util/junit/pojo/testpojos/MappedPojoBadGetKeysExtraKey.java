package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A {@link MappedPojo} used for testing where {@link #getKeys()} returns an extra key if there are keys
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public class MappedPojoBadGetKeysExtraKey<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoBadGetKeysExtraKey(){
		map = new HashMap<>();
	}
	
	@Override
	public Set<String> getKeys(){
		Set<String> keys = new HashSet<>(map.keySet());
		if(!keys.isEmpty()){
			keys.add(DEFAULT_WRONG_KEY);
		}
		return keys;
	}
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
