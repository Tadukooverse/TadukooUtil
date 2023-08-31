package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.Map;

/**
 * A {@link MappedPojo} to use for testing where {@link #getMap()} always returns {@code null}
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public class MappedPojoNullMap<V> implements MappedPojo, DefaultTestValues{
	
	public MappedPojoNullMap(){ }
	
	@SuppressWarnings("unused")
	public MappedPojoNullMap(MappedPojo pojo){ }
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public Map<String, Object> getMap(){
		return null;
	}
}
