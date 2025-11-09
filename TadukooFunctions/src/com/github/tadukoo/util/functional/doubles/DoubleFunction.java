package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.Function;

/**
 * Represents a {@link Function} that takes a double and outputs something else
 *
 * @param <A> The output type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface DoubleFunction<A> extends Function<Double, A>{
	
	/**
	 * Returns a {@link DoubleFunction} that always returns its input argument
	 *
	 * @return A {@link DoubleFunction} that always returns its input argument
	 */
	static DoubleFunction<Double> identity(){
		return a -> a;
	}
}
