package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that sets the wrong item in the setter
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoWrongItem<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoWrongItem(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue();
	
	protected abstract V getDefaultWrongValue();
	
	public V getTest(){
		return getDefaultTestValue();
	}
	
	@SuppressWarnings("unused")
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, getDefaultWrongValue());
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
