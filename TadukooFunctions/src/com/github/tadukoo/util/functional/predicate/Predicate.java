package com.github.tadukoo.util.functional.predicate;

import java.util.Objects;

/**
 * A better version of Java's {@link java.util.function.Predicate} interface that
 * works better with the throwing functions.
 *
 * @param <A> The input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate<A>{
	
	/**
	 * Takes a single argument and returns a boolean.
	 *
	 * @param a The argument
	 * @return A boolean
	 */
	boolean test(A a);
	
	/**
	 * Creates a {@link Predicate} that will test the argument with this {@link Predicate}
	 * and with the given {@link Predicate}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate} to test the argument on
	 * @return The {@link Predicate} that results from composing this one and the given one
	 */
	default Predicate<A> and(Predicate<? super A> other){
		return a -> this.test(a) && other.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that will test the argument with this {@link Predicate}
	 * and with the given {@link ThrowingPredicate}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate} to test the argument on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate} that results from composing this {@link Predicate} and the given {@link ThrowingPredicate}
	 */
	default <T extends Throwable> ThrowingPredicate<A, T> and(ThrowingPredicate<? super A, ? extends T> other){
		return a -> this.test(a) && other.test(a);
	}
	
	/**
	 * Creates a {@link Predicate} that will test the argument with this {@link Predicate}
	 * and with the given {@link Predicate}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate} to test the argument on
	 * @return The {@link Predicate} that results from composing this one and the given one
	 */
	default Predicate<A> or(Predicate<? super A> other){
		return a -> this.test(a) || other.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that will test the argument with this {@link Predicate}
	 * and with the given {@link ThrowingPredicate}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate} to test the argument on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate} that results from composing this {@link Predicate} and the given {@link ThrowingPredicate}
	 */
	default <T extends Throwable> ThrowingPredicate<A, T> or(ThrowingPredicate<? super A, ? extends T> other){
		return a -> this.test(a) || other.test(a);
	}
	
	/**
	 * Creates a {@link Predicate} that will return the opposite result of this {@link Predicate}.
	 *
	 * @return A negated version of this {@link Predicate}
	 */
	default Predicate<A> negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link Predicate} that tests if two arguments are equal according
	 * to {@link Objects#equals(Object, Object)}.
	 *
	 * @param <A> The argument type
	 * @param obj The object to test against
	 * @return A {@link Predicate} that tests if two objects are equal
	 */
	static <A> Predicate<A> isEqual(Object obj){
		return a -> Objects.equals(obj, a);
	}
}
