package com.github.tadukoo.util.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Mapped Pojo is a simple implementation of the {@link MappedPojo} interface that uses a
 * HashMap as its backing Map.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.5
 * @since Alpha v.0.2
 */
public abstract class AbstractMappedPojo implements MappedPojo{
	/** The map containing the items in this pojo */
	private final Map<String, Object> map;
	
	/**
	 * Creates a new Mapped Pojo and initializes the backing Map.
	 */
	protected AbstractMappedPojo(){
		map = new HashMap<>();
	}
	
	/**
	 * Creates a new Mapped Pojo, using the map from the given pojo.
	 *
	 * @param pojo The {@link MappedPojo} to copy the map from
	 */
	protected AbstractMappedPojo(MappedPojo pojo){
		map = pojo.getMap();
	}
	
	/** {@inheritDoc} */
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
