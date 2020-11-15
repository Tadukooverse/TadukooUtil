package com.github.tadukoo.util.pojo;

/**
 * Abstract Ordered Mapped Pojo is a simple implementation of {@link OrderedMappedPojo}. It extends
 * {@link AbstractMappedPojo}, and requires its subclasses to implement the {@link #getKeyOrder()} method.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2.1
 * @since Alpha v.0.2
 */
public abstract class AbstractOrderedMappedPojo extends AbstractMappedPojo implements OrderedMappedPojo{
	
	/** {@inheritDoc} */
	protected AbstractOrderedMappedPojo(){
		super();
	}
	
	/** {@inheritDoc} */
	protected AbstractOrderedMappedPojo(MappedPojo pojo){
		super(pojo);
	}
}
