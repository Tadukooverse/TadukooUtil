package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingUnaryOperator;

/**
 * Represents a {@link ThrowingUnaryOperator} for ints
 *
 * @param <T> A {@link Throwable} that the unary operator throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntUnaryOperator<T extends Throwable> extends ThrowingUnaryOperator<Integer, T>{
	
	/**
	 * Returns a {@link ThrowingIntUnaryOperator} that always returns its input argument
	 *
	 * @param <T> A {@link Throwable} that the unary operator throws
	 * @return A {@link ThrowingIntUnaryOperator} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingIntUnaryOperator<T> identity(){
		return a -> a;
	}
}
