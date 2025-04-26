package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.predicate.Predicate;

/**
 * Represents a {@link Predicate} that accepts a double
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface DoublePredicate extends Predicate<Double>{
	
	/**
	 * Creates an {@link DoublePredicate} that will return the opposite result of this {@link DoublePredicate}.
	 *
	 * @return A negated version of this {@link DoublePredicate}
	 */
	default DoublePredicate negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link DoublePredicate} that tests if two arguments are equal.
	 *
	 * @param obj The object to test against
	 * @return A {@link DoublePredicate} that tests if two objects are equal
	 */
	static DoublePredicate isEqual(int obj){
		return a -> obj == a;
	}
}
