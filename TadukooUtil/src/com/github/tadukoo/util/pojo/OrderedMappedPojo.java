package com.github.tadukoo.util.pojo;

import java.util.List;

/**
 * Ordered Mapped Pojo represents a {@link MappedPojo} where the key order matters.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public interface OrderedMappedPojo extends MappedPojo{
	
	/**
	 * @return A List of the keys in this pojo in the proper order
	 */
	List<String> getKeyOrder();
}
