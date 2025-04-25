package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.Function;

/**
 * Represents a {@link Function} that takes a long and outputs something else
 *
 * @param <A> The output type
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface LongFunction<A> extends Function<Long, A>{
	
	/**
	 * Returns a {@link LongFunction} that always returns its input argument
	 *
	 * @return A {@link LongFunction} that always returns its input argument
	 */
	static LongFunction<Long> identity(){
		return a -> a;
	}
}
