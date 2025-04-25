package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.Function;

/**
 * Represents a {@link Function} that takes an int and outputs something else
 *
 * @param <A> The output type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface IntFunction<A> extends Function<Integer, A>{
	
	/**
	 * Returns an {@link IntFunction} that always returns its input argument
	 *
	 * @return An {@link IntFunction} that always returns its input argument
	 */
	static IntFunction<Integer> identity(){
		return a -> a;
	}
}
