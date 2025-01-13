package com.github.tadukoo.util.functional.predicate;

import java.util.function.BiPredicate;

/**
 * A better version of Java's {@link BiPredicate} interface that
 * works better with the throwing functions.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate2<A, B>{
	
	/**
	 * Takes two arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @return A boolean
	 */
	boolean test(A a, B b);
	
	/**
	 * Creates a {@link Predicate2} that will test the arguments with this {@link Predicate2}
	 * and with the given {@link Predicate2}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate2} to test the arguments on
	 * @return The {@link Predicate2} that results from composing this one and the given one
	 */
	default Predicate2<A, B> and(Predicate2<? super A, ? super B> other){
		return (a, b) -> this.test(a, b) && other.test(a, b);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate2} that will test the arguments with this {@link Predicate2}
	 * and with the given {@link ThrowingPredicate2}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate2} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate2} that results from composing this {@link Predicate2} and the given {@link ThrowingPredicate2}
	 */
	default <T extends Throwable> ThrowingPredicate2<A, B, T> and(
			ThrowingPredicate2<? super A, ? super B, ? extends T> other){
		return (a, b) -> this.test(a, b) && other.test(a, b);
	}
	
	/**
	 * Creates a {@link Predicate2} that will test the arguments with this {@link Predicate2}
	 * and with the given {@link Predicate2}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate2} to test the arguments on
	 * @return The {@link Predicate2} that results from composing this one and the given one
	 */
	default Predicate2<A, B> or(Predicate2<? super A, ? super B> other){
		return (a, b) -> this.test(a, b) || other.test(a, b);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate2} that will test the arguments with this {@link Predicate2}
	 * and with the given {@link ThrowingPredicate2}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate2} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate2} that results from composing this {@link Predicate2} and the given {@link ThrowingPredicate2}
	 */
	default <T extends Throwable> ThrowingPredicate2<A, B, T> or(
			ThrowingPredicate2<? super A, ? super B, ? extends T> other){
		return (a, b) -> this.test(a, b) || other.test(a, b);
	}
	
	/**
	 * Creates a {@link Predicate2} that will return the opposite result of this {@link Predicate2}.
	 *
	 * @return A negated version of this {@link Predicate2}
	 */
	default Predicate2<A, B> negate(){
		return (a, b) -> !this.test(a, b);
	}
}
