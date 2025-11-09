package com.github.tadukoo.util.functional.function;

import java.util.Comparator;

/**
 * A {@link Function2} that takes the same type of arguments as input and returns the same type.
 *
 * @param <A> The type of the inputs and the result
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface BinaryOperator<A> extends Function2<A, A, A>{
	
	/**
	 * Creates a {@link BinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param <A> The type of the inputs to the {@link BinaryOperator}
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link BinaryOperator} that returns the min value of the inputs
	 */
	static <A> BinaryOperator<A> minBy(Comparator<? super A> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates a {@link BinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param <A> The type of the inputs to the {@link BinaryOperator}
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link BinaryOperator} that returns the max value of the inputs
	 */
	static <A> BinaryOperator<A> maxBy(Comparator<? super A> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
