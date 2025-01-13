package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes three arguments and returns a boolean.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate3<A, B, C>{
	
	/**
	 * Takes three arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c);
	
	/**
	 * Creates a {@link Predicate3} that will test the arguments with this {@link Predicate3}
	 * and with the given {@link Predicate3}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate3} to test the arguments on
	 * @return The {@link Predicate3} that results from composing this one and the given one
	 */
	default Predicate3<A, B, C> and(Predicate3<? super A, ? super B, ? super C> other){
		return (a, b, c) -> this.test(a, b, c) && other.test(a, b, c);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate3} that will test the arguments with this {@link Predicate3}
	 * and with the given {@link ThrowingPredicate3}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate3} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate3} that results from composing this {@link Predicate3} and the given {@link ThrowingPredicate3}
	 */
	default <T extends Throwable> ThrowingPredicate3<A, B, C, T> and(
			ThrowingPredicate3<? super A, ? super B, ? super C, ? extends T> other){
		return (a, b, c) -> this.test(a, b, c) && other.test(a, b, c);
	}
	
	/**
	 * Creates a {@link Predicate3} that will test the arguments with this {@link Predicate3}
	 * and with the given {@link Predicate3}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate3} to test the arguments on
	 * @return The {@link Predicate3} that results from composing this one and the given one
	 */
	default Predicate3<A, B, C> or(Predicate3<? super A, ? super B, ? super C> other){
		return (a, b, c) -> this.test(a, b, c) || other.test(a, b, c);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate3} that will test the arguments with this {@link Predicate3}
	 * and with the given {@link ThrowingPredicate3}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate3} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate3} that results from composing this {@link Predicate3} and the given {@link ThrowingPredicate3}
	 */
	default <T extends Throwable> ThrowingPredicate3<A, B, C, T> or(
			ThrowingPredicate3<? super A, ? super B, ? super C, ? extends T> other){
		return (a, b, c) -> this.test(a, b, c) || other.test(a, b, c);
	}
	
	/**
	 * Creates a {@link Predicate3} that will return the opposite result of this {@link Predicate3}.
	 *
	 * @return A negated version of this {@link Predicate3}
	 */
	default Predicate3<A, B, C> negate(){
		return (a, b, c) -> !this.test(a, b, c);
	}
}
