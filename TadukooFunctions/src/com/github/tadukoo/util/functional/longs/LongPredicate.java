package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.predicate.Predicate;

/**
 * Represents a {@link Predicate} that accepts a long
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface LongPredicate extends Predicate<Long>{
	
	/**
	 * Creates an {@link LongPredicate} that will return the opposite result of this {@link LongPredicate}.
	 *
	 * @return A negated version of this {@link LongPredicate}
	 */
	default LongPredicate negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link LongPredicate} that tests if two arguments are equal.
	 *
	 * @param obj The object to test against
	 * @return A {@link LongPredicate} that tests if two objects are equal
	 */
	static LongPredicate isEqual(int obj){
		return a -> obj == a;
	}
}
