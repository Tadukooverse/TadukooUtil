package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;

/**
 * Represents a {@link Function} that takes something and outputs a long
 *
 * @param <A> The input type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ToLongFunction<A> extends Function<A, Long>{
	
	/**
	 * Returns a {@link ToLongFunction} that always returns its input argument
	 *
	 * @return A {@link ToLongFunction} that always returns its input argument
	 */
	static ToLongFunction<Long> identity(){
		return a -> a;
	}
}
