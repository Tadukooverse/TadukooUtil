package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A {@link MappedPojo} used for testing that returns {@code null} for {@link #getKeys()} once there are values
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public class MappedPojoCustomNullKeys<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	private boolean doBad = false;
	
	public MappedPojoCustomNullKeys(){
		map = new HashMap<>();
	}
	
	@Override
	public Set<String> getKeys(){
		if(doBad){
			return null;
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
		doBad = true;
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
