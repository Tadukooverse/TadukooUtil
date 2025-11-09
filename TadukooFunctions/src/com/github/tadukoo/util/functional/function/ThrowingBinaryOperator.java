package com.github.tadukoo.util.functional.function;

import java.util.Comparator;

/**
 * A {@link ThrowingFunction2} that takes the same type of arguments as input and returns the same type.
 *
 * @param <A> The type of the inputs and the result
 * @param <T> The type of {@link Throwable} thrown by the {@link ThrowingBinaryOperator}
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingBinaryOperator<A, T extends Throwable> extends ThrowingFunction2<A, A, A, T>{
	
	/**
	 * Creates a {@link ThrowingBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param <A> The type of the inputs to the {@link ThrowingBinaryOperator}
	 * @param <T> The type of {@link Throwable} thrown by the {@link ThrowingBinaryOperator}
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link ThrowingBinaryOperator} that returns the min value of the inputs
	 */
	static <A, T extends Throwable> ThrowingBinaryOperator<A, T> minBy(Comparator<? super A> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates a {@link ThrowingBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param <A> The type of the inputs to the {@link ThrowingBinaryOperator}
	 * @param <T> The type of {@link Throwable} thrown by the {@link ThrowingBinaryOperator}
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link ThrowingBinaryOperator} that returns the max value of the inputs
	 */
	static <A, T extends Throwable> ThrowingBinaryOperator<A, T> maxBy(Comparator<? super A> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
