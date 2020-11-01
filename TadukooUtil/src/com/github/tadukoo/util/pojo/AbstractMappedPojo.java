package com.github.tadukoo.util.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Abstract Mapped Pojo is a simple implementation of the {@link MappedPojo} interface that uses a
 * HashMap as its backing Map.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
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
	
	/** {@inheritDoc} */
	@Override
	public boolean hasKey(String key){
		return map.containsKey(key);
	}
	
	/** {@inheritDoc} */
	@Override
	public Set<String> getKeys(){
		return map.keySet();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean hasItem(String key){
		return map.get(key) != null;
	}
	
	/** {@inheritDoc} */
	@Override
	public Object getItem(String key){
		return map.get(key);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setItem(String key, Object item){
		map.put(key, item);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeItem(String key){
		map.remove(key);
	}
	
	/** {@inheritDoc} */
	@Override
	public Map<String, Object> getMap(){
		return map;
	}
}
