package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A {@link MappedPojo} used for testing that adds an extra key once we have values on run 2
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoCustomExtraKey2<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	private boolean doBad = false;
	
	public MappedPojoCustomExtraKey2(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue2();
	
	@Override
	public Set<String> getKeys(){
		if(doBad){
			Set<String> keys = new HashSet<>(map.keySet());
			keys.add(DEFAULT_WRONG_KEY);
			return keys;
		}else{
			return map.keySet();
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
