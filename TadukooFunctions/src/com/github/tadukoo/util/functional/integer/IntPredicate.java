package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.predicate.Predicate;

/**
 * Represents a {@link Predicate} that accepts an int
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface IntPredicate extends Predicate<Integer>{
	
	/**
	 * Creates an {@link IntPredicate} that will return the opposite result of this {@link IntPredicate}.
	 *
	 * @return A negated version of this {@link IntPredicate}
	 */
	default IntPredicate negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link IntPredicate} that tests if two arguments are equal.
	 *
	 * @param obj The object to test against
	 * @return A {@link IntPredicate} that tests if two objects are equal
	 */
	static IntPredicate isEqual(int obj){
		return a -> obj == a;
	}
}
