package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that makes the map non-empty on {@link #clear()}
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public class MappedPojoBadClearNonEmptyMap<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	private boolean doBad;
	
	public MappedPojoBadClearNonEmptyMap(){
		map = new HashMap<>();
		doBad = false;
	}
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public void clear(){
		map.clear();
		doBad = true;
	}
	
	@Override
	public boolean isEmpty(){
		if(doBad){
			return true;
		}else{
			return map.isEmpty();
		}
	}
	
	@Override
	public Map<String, Object> getMap(){
		if(doBad){
			map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
		}
		return map;
	}
}
