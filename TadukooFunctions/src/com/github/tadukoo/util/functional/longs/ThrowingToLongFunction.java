package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes something and returns a long
 *
 * @param <A> The input type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingToLongFunction<A, T extends Throwable> extends ThrowingFunction<A, Long, T>{
	
	/**
	 * Returns a {@link ThrowingToLongFunction} that always returns its input argument
	 *
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingToLongFunction} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingToLongFunction<Long, T> identity(){
		return a -> a;
	}
}
