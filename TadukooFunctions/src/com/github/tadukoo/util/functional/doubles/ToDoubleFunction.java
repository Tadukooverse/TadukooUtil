package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.Function;

/**
 * Represents a {@link Function} that takes something and outputs a double
 *
 * @param <A> The input type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ToDoubleFunction<A> extends Function<A, Double>{
	
	/**
	 * Returns a {@link ToDoubleFunction} that always returns its input argument
	 *
	 * @return A {@link ToDoubleFunction} that always returns its input argument
	 */
	static ToDoubleFunction<Double> identity(){
		return a -> a;
	}
}
