package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing where {@link #getMap()} returns with an extra value if it's not empty on run 2
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoBadGetMapExtraItem2<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoBadGetMapExtraItem2(){
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
	public Map<String, Object> getMap(){
		Object val = map.get(DEFAULT_TEST_KEY);
		if(!map.isEmpty() && val.equals(getDefaultTestValue2())){
			map.put(DEFAULT_WRONG_KEY, DEFAULT_WRONG_STRING);
		}
		return map;
	}
}
