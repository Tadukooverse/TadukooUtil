package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingUnaryOperator;

/**
 * Represents a {@link ThrowingUnaryOperator} for longs
 *
 * @param <T> A {@link Throwable} that the unary operator throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongUnaryOperator<T extends Throwable> extends ThrowingUnaryOperator<Long, T>{
	
	/**
	 * Returns a {@link ThrowingLongUnaryOperator} that always returns its input argument
	 *
	 * @param <T> A {@link Throwable} that the unary operator throws
	 * @return A {@link ThrowingLongUnaryOperator} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingLongUnaryOperator<T> identity(){
		return a -> a;
	}
}
