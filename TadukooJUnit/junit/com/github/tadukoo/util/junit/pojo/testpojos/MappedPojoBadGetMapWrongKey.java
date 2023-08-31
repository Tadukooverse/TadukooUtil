package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A {@link MappedPojo} used for testing where {@link #getMap()} returns with the wrong key if it's not empty
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public class MappedPojoBadGetMapWrongKey<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoBadGetMapWrongKey(){
		map = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public boolean hasKey(String key){
		return map.containsKey(key);
	}
	
	@Override
	public boolean hasItem(String key){
		return map.get(key) != null;
	}
	
	@Override
	public Set<String> getKeys(){
		return map.keySet();
	}
	
	@Override
	public Object getItem(String key){
		return map.get(key);
	}
	
	@Override
	public Map<String, Object> getMap(){
		if(!map.isEmpty()){
			map.remove(DEFAULT_TEST_KEY);
			map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
		}
		return map;
	}
}
