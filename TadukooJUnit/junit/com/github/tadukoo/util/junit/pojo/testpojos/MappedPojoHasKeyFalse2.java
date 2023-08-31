package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that doesn't set a value during {@link #setTest(Object)}, so
 * {@link #hasKey(String)} will return false on run 2
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoHasKeyFalse2<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	private boolean doBad = false;
	
	public MappedPojoHasKeyFalse2(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue2();
	
	@Override
	public boolean hasKey(String key){
		if(doBad){
			return false;
		}else{
			return map.containsKey(key);
		}
	}
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
		if(value.equals(getDefaultTestValue2())){
			doBad = true;
		}
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
