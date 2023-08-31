package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that returns the wrong value in the map using {@link #getMap()}
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoCustomWrongMapValue<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	private boolean doBad = false;
	
	public MappedPojoCustomWrongMapValue(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultWrongValue();
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) map.get(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
		doBad = true;
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
	@SuppressWarnings("unchecked")
	public V getItem(String key){
		return (V) map.get(key);
	}
	
	@Override
	public Map<String, Object> getMap(){
		if(doBad){
			map.put(DEFAULT_TEST_KEY, getDefaultWrongValue());
		}
		return map;
	}
}
