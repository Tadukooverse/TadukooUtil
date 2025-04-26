package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingBinaryOperator;

import java.util.Comparator;

/**
 * Represents a {@link ThrowingBinaryOperator} for doubles
 *
 * @param <T> A {@link Throwable} that can be thrown by the binary operator
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoubleBinaryOperator<T extends Throwable> extends ThrowingBinaryOperator<Double, T>{
	
	/**
	 * Creates a {@link ThrowingDoubleBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param <T> A {@link Throwable} thrown by the binary operator
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link ThrowingDoubleBinaryOperator} that returns the min value of the inputs
	 */
	static <T extends Throwable> ThrowingDoubleBinaryOperator<T> minBy(Comparator<? super Double> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates a {@link ThrowingDoubleBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param <T> A {@link Throwable} thrown by the binary operator
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link ThrowingDoubleBinaryOperator} that returns the max value of the inputs
	 */
	static <T extends Throwable> ThrowingDoubleBinaryOperator<T> maxBy(Comparator<? super Double> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
