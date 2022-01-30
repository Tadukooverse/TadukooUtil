package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A good {@link MappedPojo} to use for passing tests
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public class MappedPojoA<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoA(){
		map = new HashMap<>();
	}
	
	@SuppressWarnings("unused")
	public MappedPojoA(MappedPojo pojo){
		map = pojo.getMap();
	}
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
