package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.UnaryOperator;

/**
 * Represents a {@link UnaryOperator} for longs
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface LongUnaryOperator extends UnaryOperator<Long>{
	
	/**
	 * Returns a {@link LongUnaryOperator} that always returns its input argument
	 *
	 * @return A {@link LongUnaryOperator} that always returns its input argument
	 */
	static LongUnaryOperator identity(){
		return a -> a;
	}
}
