package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.BinaryOperator;

import java.util.Comparator;

/**
 * Represents a {@link BinaryOperator} for doubles
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface DoubleBinaryOperator extends BinaryOperator<Double>{
	
	/**
	 * Creates a {@link DoubleBinaryOperator} that returns the min value of the inputs using the given {@link Comparator}
	 *
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link DoubleBinaryOperator} that returns the min value of the inputs
	 */
	static DoubleBinaryOperator minBy(Comparator<? super Double> comparator){
		return (a, b) -> comparator.compare(a, b) <= 0?a:b;
	}
	
	/**
	 * Creates a {@link DoubleBinaryOperator} that returns the max value of the inputs using the given {@link Comparator}
	 *
	 * @param comparator The {@link Comparator} to use to compare inputs
	 * @return A {@link DoubleBinaryOperator} that returns the max value of the inputs
	 */
	static DoubleBinaryOperator maxBy(Comparator<? super Double> comparator){
		return (a, b) -> comparator.compare(a, b) >= 0?a:b;
	}
}
