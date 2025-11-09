package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes something and returns an int
 *
 * @param <A> The input type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingToIntFunction<A, T extends Throwable> extends ThrowingFunction<A, Integer, T>{
	
	/**
	 * Returns a {@link ThrowingToIntFunction} that always returns its input argument
	 *
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingToIntFunction} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingToIntFunction<Integer, T> identity(){
		return a -> a;
	}
}
