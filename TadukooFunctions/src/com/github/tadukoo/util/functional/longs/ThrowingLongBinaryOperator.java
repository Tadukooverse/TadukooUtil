package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingBinaryOperator;

import java.util.Comparator;

/**
 * Represents a {@link ThrowingBinaryOperator} for longs
 *
 * @param <T> A {@link Throwable} that can be thrown by the binary operator
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongBinaryOperator<T extends Throwable> extends ThrowingBinaryOperator<Long, T>{
	
	/**
	 * Creates a {@link ThrowingLongBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param <T> A {@link Throwable} thrown by the binary operator
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link ThrowingLongBinaryOperator} that returns the min value of the inputs
	 */
	static <T extends Throwable> ThrowingLongBinaryOperator<T> minBy(Comparator<? super Long> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates a {@link ThrowingLongBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param <T> A {@link Throwable} thrown by the binary operator
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link ThrowingLongBinaryOperator} that returns the max value of the inputs
	 */
	static <T extends Throwable> ThrowingLongBinaryOperator<T> maxBy(Comparator<? super Long> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
