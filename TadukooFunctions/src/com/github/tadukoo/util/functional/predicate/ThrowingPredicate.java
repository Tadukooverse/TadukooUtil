package com.github.tadukoo.util.functional.predicate;

import java.util.Objects;

/**
 * A better version of Java's {@link java.util.function.Predicate} interface that
 * allows for the predicates to throw whatever {@link Throwable}
 * is specified.
 *
 * @param <A> The input argument type for the predicate
 * @param <T> The type of {@link Throwable} thrown by the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate<A, T extends Throwable>{
	
	/**
	 * Takes a single argument and returns a boolean.
	 * 
	 * @param a The argument
	 * @return A boolean
	 * @throws T Determined by the predicate, not required
	 */
	boolean test(A a) throws T;
	
	/**
	 * Creates a {@link ThrowingPredicate} that will test the argument with this {@link ThrowingPredicate}
	 * and with the given {@link ThrowingPredicate}, returning true only if both results are true.
	 * 
	 * @param other The other {@link ThrowingPredicate} to test the argument on
	 * @return The {@link ThrowingPredicate} that results from composing this one and the given one
	 */
	default ThrowingPredicate<A, T> and(ThrowingPredicate<? super A, ? extends T> other){
		return a -> this.test(a) && other.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that will test the argument with this {@link ThrowingPredicate}
	 * and with the given {@link Predicate}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate} to test the argument on
	 * @return The {@link ThrowingPredicate} that results from composing this {@link ThrowingPredicate} and the given {@link Predicate}
	 */
	default ThrowingPredicate<A, T> and(Predicate<? super A> other){
		return a -> this.test(a) && other.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that will test the argument with this {@link ThrowingPredicate}
	 * and with the given {@link ThrowingPredicate}, returning true if either result is true.
	 * 
	 * @param other The other {@link ThrowingPredicate} to test the argument on
	 * @return The {@link ThrowingPredicate} that results from composing this one and the given one
	 */
	default ThrowingPredicate<A, T> or(ThrowingPredicate<? super A, ? extends T> other){
		return a -> this.test(a) || other.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that will test the argument with this {@link ThrowingPredicate}
	 * and with the given {@link Predicate}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate} to test the argument on
	 * @return The {@link ThrowingPredicate} that results from composing this {@link ThrowingPredicate} and the given {@link Predicate}
	 */
	default ThrowingPredicate<A, T> or(Predicate<? super A> other){
		return a -> this.test(a) || other.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that will return the opposite result of this {@link ThrowingPredicate}.
	 * 
	 * @return A negated version of this {@link ThrowingPredicate}
	 */
	default ThrowingPredicate<A, T> negate(){
		return a -> !this.test(a);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate} that tests if two arguments are equal according
	 * to {@link Objects#equals(Object, Object)}.
	 * 
	 * @param <A> The argument type
	 * @param <T> The {@link Throwable} being thrown
	 * @param obj The object to test against
	 * @return A ThrowingPredicate that tests if two objects are equal
	 */
	static <A, T extends Throwable> ThrowingPredicate<A, T> isEqual(Object obj){
		return a -> Objects.equals(obj, a);
	}
}
