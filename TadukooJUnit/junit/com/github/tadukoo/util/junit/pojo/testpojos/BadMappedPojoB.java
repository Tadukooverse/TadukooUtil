package com.github.tadukoo.util.junit.pojo.testpojos;

import com.github.tadukoo.util.pojo.MappedPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used for tests involving a private empty (no parameters) constructor for a {@link MappedPojo}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 * @since Beta v.0.6 (in Tadukoo Util project); Alpha v.0.1 (in Tadukoo JUnit project)
 */
public class BadMappedPojoB implements MappedPojo{
	private final Map<String, Object> map;
	
	@SuppressWarnings("unused")
	private BadMappedPojoB(){
		map = new HashMap<>();
	}
	
	@SuppressWarnings("unused")
	private BadMappedPojoB(MappedPojo pojo){
		map = pojo.getMap();
	}
	
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
