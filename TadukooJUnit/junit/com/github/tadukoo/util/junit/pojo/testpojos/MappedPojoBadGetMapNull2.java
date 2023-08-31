package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A {@link MappedPojo} used for testing where {@link #getMap()} returns null if it's not empty on run 2
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
public abstract class MappedPojoBadGetMapNull2<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoBadGetMapNull2(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue2();
	
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
		Object val = map.get(DEFAULT_TEST_KEY);
		if(!map.isEmpty() && val.equals(getDefaultTestValue2())){
			return null;
		}
		return map;
	}
}
