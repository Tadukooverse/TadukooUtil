package com.github.tadukoo.util.view;

import java.awt.*;

/**
 * This class provides utility methods for dealing with {@link Insets}
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 */
public final class InsetsUtil{
	
	// Can't create InsetsUtil
	private InsetsUtil(){ }
	
	/**
	 * Sets the given {@link Insets} to 0, unless they're null (then we create a brand new {@link Insets} with zeroed
	 * values.
	 *
	 * @param insets The {@link Insets} object to set to 0, or null
	 * @return An {@link Insets} object with zeroed values
	 */
	public static Insets zeroInsets(Insets insets){
		if(insets == null){
			return new Insets(0, 0, 0, 0);
		}else{
			insets.top = insets.left = insets.right = insets.bottom = 0;
			return insets;
		}
	}
	
	/**
	 * Adds the given additional {@link Insets} to the source {@link Insets}. If either is null, they're treated as
	 * 0 insets.
	 *
	 * @param source The {@link Insets} to be added to
	 * @param addition The {@link Insets} to use for adding
	 * @return The source {@link Insets} with their new values
	 */
	public static Insets addInsets(Insets source, Insets addition){
		// If source is null, change to zeroed insets
		if(source == null){
			source = new Insets(0, 0, 0, 0);
		}
		
		// If addition is null, there's no adding needed
		if(addition != null){
			source.set(source.top + addition.top, source.left + addition.left,
					source.bottom + addition.bottom, source.right + addition.right);
		}
		
		// Return the source Insets
		return source;
	}
}
