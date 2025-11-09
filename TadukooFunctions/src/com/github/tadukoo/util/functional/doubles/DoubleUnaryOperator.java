package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.UnaryOperator;

/**
 * Represents a {@link UnaryOperator} for doubles
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface DoubleUnaryOperator extends UnaryOperator<Double>{
	
	/**
	 * Returns a {@link DoubleUnaryOperator} that always returns its input argument
	 *
	 * @return A {@link DoubleUnaryOperator} that always returns its input argument
	 */
	static DoubleUnaryOperator identity(){
		return a -> a;
	}
}
