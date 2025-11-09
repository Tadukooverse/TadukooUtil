package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.Function;

/**
 * Represents a {@link Function} that takes something and outputs an int
 *
 * @param <A> The input type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ToIntFunction<A> extends Function<A, Integer>{
	
	/**
	 * Returns an {@link ToIntFunction} that always returns its input argument
	 *
	 * @return An {@link ToIntFunction} that always returns its input argument
	 */
	static ToIntFunction<Integer> identity(){
		return a -> a;
	}
}
