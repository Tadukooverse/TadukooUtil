package com.github.tadukoo.util.functional.predicate;

import java.util.function.BiPredicate;

/**
 * A better version of Java's {@link BiPredicate} interface that 
 * allows for the predicates to throw whatever {@link Throwable}
 * is specified.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <T> The type of {@link Throwable} thrown by the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate2<A, B, T extends Throwable>{
	
	/**
	 * Takes two arguments and returns a boolean.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @return A boolean
	 * @throws T Determined by the predicate, not required
	 */
	boolean test(A a, B b) throws T;
	
	/**
	 * Creates a ThrowingPredicate2 that will test the arguments with this ThrowingPredicate2 
	 * and with the given ThrowingPredicate2, returning true only if both results are true.
	 * 
	 * @param other The other ThrowingPredicate2 to test the arguments on
	 * @return The ThrowingPredicate2 that results from composing this one and the given one
	 */
	default ThrowingPredicate2<A, B, T> and(ThrowingPredicate2<? super A, ? super B, ? extends T> other){
		return (a, b) -> this.test(a, b) && other.test(a, b);
	}
	
	/**
	 * Creates a ThrowingPredicate2 that will test the arguments with this ThrowingPredicate2 
	 * and with the given ThrowingPredicate2, returning true if either result is true.
	 * 
	 * @param other The other ThrowingPredicate2 to test the arguments on
	 * @return The ThrowingPredicate2 that results from composing this one and the given one
	 */
	default ThrowingPredicate2<A, B, T> or(ThrowingPredicate2<? super A, ? super B, ? extends T> other){
		return (a, b) -> this.test(a, b) || other.test(a, b);
	}
	
	/**
	 * Creates a ThrowingPredicate2 that will return the opposite result of this ThrowingPredicate2.
	 * 
	 * @return A negated version of this ThrowingPredicate2
	 */
	default ThrowingPredicate2<A, B, T> negate(){
		return (a, b) -> !this.test(a, b);
	}
}
