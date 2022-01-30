package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing that sets the item to {@code null} in the setter so that
 * {@link #hasKey(String)} will return {@code true}, but {@link #hasItem(String)} will return {@code false}
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoHasItemFalse<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoHasItemFalse(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue();
	
	public V getTest(){
		return getDefaultTestValue();
	}
	
	@SuppressWarnings("unused")
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, null);
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
