package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingUnaryOperator;

/**
 * Represents a {@link ThrowingUnaryOperator} for doubles
 *
 * @param <T> A {@link Throwable} that the unary operator throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoubleUnaryOperator<T extends Throwable> extends ThrowingUnaryOperator<Double, T>{
	
	/**
	 * Returns a {@link ThrowingDoubleUnaryOperator} that always returns its input argument
	 *
	 * @param <T> A {@link Throwable} that the unary operator throws
	 * @return A {@link ThrowingDoubleUnaryOperator} that always returns its input argument
	 */
	static <T extends Throwable> ThrowingDoubleUnaryOperator<T> identity(){
		return a -> a;
	}
}
