package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.junit.DefaultTestValues;
import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link MappedPojo} used for testing where the custom getter returns a wrong value on run 2
 *
 * @param <V> The type to use for the getter + setter
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util); Alpha v.0.1 (in Tadukoo JUnit)
 */
public abstract class MappedPojoBadGetter2<V> implements MappedPojo, DefaultTestValues{
	private final Map<String, Object> map;
	
	public MappedPojoBadGetter2(){
		map = new HashMap<>();
	}
	
	protected abstract V getDefaultTestValue2();
	
	protected abstract V getDefaultWrongValue();
	
	@SuppressWarnings("unchecked")
	public V getTest(){
		V val = (V) getItem(DEFAULT_TEST_KEY);
		if(getDefaultTestValue2().equals(val)){
			return getDefaultWrongValue();
		}else{
			return val;
		}
	}
	
	public void setTest(V value){
		setItem(DEFAULT_TEST_KEY, value);
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
