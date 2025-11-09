package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.BinaryOperator;

import java.util.Comparator;

/**
 * Represents a {@link BinaryOperator} for ints
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface IntBinaryOperator extends BinaryOperator<Integer>{
	
	/**
	 * Creates an {@link IntBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return An {@link IntBinaryOperator} that returns the min value of the inputs
	 */
	static IntBinaryOperator minBy(Comparator<? super Integer> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates an {@link IntBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return An {@link IntBinaryOperator} that returns the max value of the inputs
	 */
	static IntBinaryOperator maxBy(Comparator<? super Integer> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
