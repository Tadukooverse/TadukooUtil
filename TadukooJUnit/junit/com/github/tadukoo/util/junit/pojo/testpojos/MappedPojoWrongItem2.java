package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that sets the wrong item in the setter on run 2
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoWrongItem2<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	private boolean doBad;
	
	public MappedPojoWrongItem2(){
		map = new HashMap<>();
		doBad = false;
	}
	
	protected abstract V getDefaultTestValue2();
	
	protected abstract V getDefaultWrongValue();
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) map.get(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		if(value.equals(getDefaultTestValue2())){
			doBad = true;
		}
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public V getItem(String key){
		if(doBad){
			return getDefaultWrongValue();
		}else{
			return (V) map.get(key);
		}
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
