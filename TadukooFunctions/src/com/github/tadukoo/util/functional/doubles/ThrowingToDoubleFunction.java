package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes something and returns a double
 *
 * @param <A> The input type
 * @param <T> A {@link Throwable} that the function throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingToDoubleFunction<A, T extends Throwable> extends ThrowingFunction<A, Double, T>{
	
	/**
	 * Returns a {@link ThrowingToDoubleFunction} that always returns its input argument
	 *
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingToDoubleFunction} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingToDoubleFunction<Double, T> identity(){
		return a -> a;
	}
}
