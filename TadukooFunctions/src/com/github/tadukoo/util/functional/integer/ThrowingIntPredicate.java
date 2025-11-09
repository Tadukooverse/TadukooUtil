package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;

/**
 * Represents a {@link ThrowingPredicate} that accepts an int
 *
 * @param <T> A {@link Throwable} that the predicate throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntPredicate<T extends Throwable> extends ThrowingPredicate<Integer, T>{
	
	/**
	 * Creates a {@link ThrowingIntPredicate} that will return the opposite result of this {@link ThrowingIntPredicate}.
	 *
	 * @return A negated version of this {@link ThrowingIntPredicate}
	 */
	default ThrowingIntPredicate<T> negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingIntPredicate} that tests if two arguments are equal.
	 *
	 * @param <T> A {@link Throwable} this predicate throws
	 * @param obj The object to test against
	 * @return A {@link ThrowingIntPredicate} that tests if two objects are equal
	 */
	static <T extends Throwable> ThrowingIntPredicate<T> isEqual(int obj){
		return a -> obj == a;
	}
}
