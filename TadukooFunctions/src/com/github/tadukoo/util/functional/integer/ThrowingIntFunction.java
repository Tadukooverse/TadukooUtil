package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes an int and returns something else
 *
 * @param <A> The return type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntFunction<A, T extends Throwable> extends ThrowingFunction<Integer, A, T>{
	
	/**
	 * Returns a {@link ThrowingIntFunction} that always returns its input argument
	 *
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingIntFunction} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingIntFunction<Integer, T> identity(){
		return a -> a;
	}
}
