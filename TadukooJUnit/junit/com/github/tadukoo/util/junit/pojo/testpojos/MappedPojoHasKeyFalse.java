package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that doesn't set a value during {@link #setTest(Object)}, so
 * {@link #hasKey(String)} will return false
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoHasKeyFalse<V> implements MappedPojo{
	private final Map<String, Object> map;
	
	public MappedPojoHasKeyFalse(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue();
	
	public V getTest(){
		return getDefaultTestValue();
	}
	
	@SuppressWarnings("unused")
	public void setTest(V value){
		// just don't set it
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
