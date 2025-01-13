package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes six arguments and returns a boolean.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <E> The 5th input argument type for the predicate
 * @param <F> The 6th input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate6<A, B, C, D, E, F>{
	
	/**
	 * Takes six arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c, D d, E e, F f);
	
	/**
	 * Creates a {@link Predicate6} that will test the arguments with this {@link Predicate6}
	 * and with the given {@link Predicate6}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate6} to test the arguments on
	 * @return The {@link Predicate6} that results from composing this one and the given one
	 */
	default Predicate6<A, B, C, D, E, F> and(
			Predicate6<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F> other){
		return (a, b, c, d, e, f) -> this.test(a, b, c, d, e, f) && other.test(a, b, c, d, e, f);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate6} that will test the arguments with this {@link Predicate6}
	 * and with the given {@link ThrowingPredicate6}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate6} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate6} that results from composing this {@link Predicate6} and the given {@link ThrowingPredicate6}
	 */
	default <T extends Throwable> ThrowingPredicate6<A, B, C, D, E, F, T> and(
			ThrowingPredicate6<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? extends T> other){
		return (a, b, c, d, e, f) -> this.test(a, b, c, d, e, f) && other.test(a, b, c, d, e, f);
	}
	
	/**
	 * Creates a {@link Predicate6} that will test the arguments with this {@link Predicate6}
	 * and with the given {@link Predicate6}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate6} to test the arguments on
	 * @return The {@link Predicate6} that results from composing this one and the given one
	 */
	default Predicate6<A, B, C, D, E, F> or(
			Predicate6<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F> other){
		return (a, b, c, d, e, f) -> this.test(a, b, c, d, e, f) || other.test(a, b, c, d, e, f);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate6} that will test the arguments with this {@link Predicate6}
	 * and with the given {@link ThrowingPredicate6}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate6} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate6} that results from composing this {@link Predicate6} and the given {@link ThrowingPredicate6}
	 */
	default <T extends Throwable> ThrowingPredicate6<A, B, C, D, E, F, T> or(
			ThrowingPredicate6<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? extends T> other){
		return (a, b, c, d, e, f) -> this.test(a, b, c, d, e, f) || other.test(a, b, c, d, e, f);
	}
	
	/**
	 * Creates a {@link Predicate6} that will return the opposite result of this {@link Predicate6}.
	 *
	 * @return A negated version of this {@link Predicate6}
	 */
	default Predicate6<A, B, C, D, E, F> negate(){
		return (a, b, c, d, e, f) -> !this.test(a, b, c, d, e, f);
	}
}
