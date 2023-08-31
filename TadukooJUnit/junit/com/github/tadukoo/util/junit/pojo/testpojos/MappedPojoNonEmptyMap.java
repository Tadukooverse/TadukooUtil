package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.map.MapUtil;
import com.github.tadukoo.util.pojo.MappedPojo;
import com.github.tadukoo.util.tuple.Pair;

import java.util.Map;

/**
 * A {@link MappedPojo} to use for testing where {@link #getMap()} always returns a non-empty Map
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public class MappedPojoNonEmptyMap<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map = MapUtil.createMap(Pair.of(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING));
	
	public MappedPojoNonEmptyMap(){ }
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		return (V) getItem(DEFAULT_TEST_KEY);
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public boolean isEmpty(){
		return true;
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
