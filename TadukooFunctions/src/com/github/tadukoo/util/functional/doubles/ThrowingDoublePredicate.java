package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;

/**
 * Represents a {@link ThrowingPredicate} that accepts a double
 *
 * @param <T> A {@link Throwable} that the predicate throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoublePredicate<T extends Throwable> extends ThrowingPredicate<Double, T>{
	
	/**
	 * Creates a {@link ThrowingDoublePredicate} that will return the opposite result of this {@link ThrowingDoublePredicate}.
	 *
	 * @return A negated version of this {@link ThrowingDoublePredicate}
	 */
	default ThrowingDoublePredicate<T> negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingDoublePredicate} that tests if two arguments are equal.
	 *
	 * @param <T> A {@link Throwable} this predicate throws
	 * @param obj The object to test against
	 * @return A {@link ThrowingDoublePredicate} that tests if two objects are equal
	 */
	static <T extends Throwable> ThrowingDoublePredicate<T> isEqual(int obj){
		return a -> obj == a;
	}
}
