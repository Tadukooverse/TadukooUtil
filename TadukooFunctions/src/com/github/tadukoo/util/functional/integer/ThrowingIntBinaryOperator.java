package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingBinaryOperator;

import java.util.Comparator;

/**
 * Represents a {@link ThrowingBinaryOperator} for ints
 *
 * @param <T> A {@link Throwable} that can be thrown by the binary operator
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntBinaryOperator<T extends Throwable> extends ThrowingBinaryOperator<Integer, T>{
	
	/**
	 * Creates an {@link ThrowingIntBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param <T> A {@link Throwable} thrown by the binary operator
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return An {@link ThrowingIntBinaryOperator} that returns the min value of the inputs
	 */
	static <T extends Throwable> ThrowingIntBinaryOperator<T> minBy(Comparator<? super Integer> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates an {@link ThrowingIntBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param <T> A {@link Throwable} thrown by the binary operator
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return An {@link ThrowingIntBinaryOperator} that returns the max value of the inputs
	 */
	static <T extends Throwable> ThrowingIntBinaryOperator<T> maxBy(Comparator<? super Integer> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
