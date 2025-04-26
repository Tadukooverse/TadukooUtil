package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.UnaryOperator;

/**
 * Represents a {@link UnaryOperator} for ints
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface IntUnaryOperator extends UnaryOperator<Integer>{
	
	/**
	 * Returns an {@link IntUnaryOperator} that always returns its input argument
	 *
	 * @return An {@link IntUnaryOperator} that always returns its input argument
	 */
	static IntUnaryOperator identity(){
		return a -> a;
	}
}
