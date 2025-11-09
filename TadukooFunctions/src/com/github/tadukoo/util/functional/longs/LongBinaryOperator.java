package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.BinaryOperator;

import java.util.Comparator;

/**
 * Represents a {@link BinaryOperator} for longs
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface LongBinaryOperator extends BinaryOperator<Long>{
	
	/**
	 * Creates a {@link LongBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link LongBinaryOperator} that returns the min value of the inputs
	 */
	static LongBinaryOperator minBy(Comparator<? super Long> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates a {@link LongBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link LongBinaryOperator} that returns the max value of the inputs
	 */
	static LongBinaryOperator maxBy(Comparator<? super Long> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
