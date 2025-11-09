package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes a double and returns something else
 *
 * @param <A> The return type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoubleFunction<A, T extends Throwable> extends ThrowingFunction<Double, A, T>{
	
	/**
	 * Returns a {@link ThrowingDoubleFunction} that always returns its input argument
	 *
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingDoubleFunction} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingDoubleFunction<Double, T> identity(){
		return a -> a;
	}
}
