package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.predicate.ThrowingPredicate;

/**
 * Represents a {@link ThrowingPredicate} that accepts a long
 *
 * @param <T> A {@link Throwable} that the predicate throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongPredicate<T extends Throwable> extends ThrowingPredicate<Long, T>{
	
	/**
	 * Creates a {@link ThrowingLongPredicate} that will return the opposite result of this {@link ThrowingLongPredicate}.
	 *
	 * @return A negated version of this {@link ThrowingLongPredicate}
	 */
	default ThrowingLongPredicate<T> negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingLongPredicate} that tests if two arguments are equal.
	 *
	 * @param <T> A {@link Throwable} this predicate throws
	 * @param obj The object to test against
	 * @return A {@link ThrowingLongPredicate} that tests if two objects are equal
	 */
	static <T extends Throwable> ThrowingLongPredicate<T> isEqual(int obj){
		return a -> obj == a;
	}
}
