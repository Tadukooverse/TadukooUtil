package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes a long and returns something else
 *
 * @param <A> The return type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongFunction<A, T extends Throwable> extends ThrowingFunction<Long, A, T>{
	
	/**
	 * Returns a {@link ThrowingLongFunction} that always returns its input argument
	 *
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingLongFunction} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingLongFunction<Long, T> identity(){
		return a -> a;
	}
}
