package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes five arguments and returns a boolean.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <E> The 5th input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate5<A, B, C, D, E>{
	
	/**
	 * Takes five arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c, D d, E e);
	
	/**
	 * Creates a {@link Predicate5} that will test the arguments with this {@link Predicate5}
	 * and with the given {@link Predicate5}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate5} to test the arguments on
	 * @return The {@link Predicate5} that results from composing this one and the given one
	 */
	default Predicate5<A, B, C, D, E> and(Predicate5<? super A, ? super B, ? super C, ? super D, ? super E> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) && other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will test the arguments with this {@link Predicate5}
	 * and with the given {@link ThrowingPredicate5}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate5} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate5} that results from composing this {@link Predicate5} and the given {@link ThrowingPredicate5}
	 */
	default <T extends Throwable> ThrowingPredicate5<A, B, C, D, E, T> and(
			ThrowingPredicate5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends T> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) && other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link Predicate5} that will test the arguments with this {@link Predicate5}
	 * and with the given {@link Predicate5}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate5} to test the arguments on
	 * @return The {@link Predicate5} that results from composing this one and the given one
	 */
	default Predicate5<A, B, C, D, E> or(Predicate5<? super A, ? super B, ? super C, ? super D, ? super E> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) || other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will test the arguments with this {@link Predicate5}
	 * and with the given {@link ThrowingPredicate5}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate5} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate5} that results from composing this {@link Predicate5} and the given {@link ThrowingPredicate5}
	 */
	default <T extends Throwable> ThrowingPredicate5<A, B, C, D, E, T> or(
			ThrowingPredicate5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends T> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) || other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link Predicate5} that will return the opposite result of this {@link Predicate5}.
	 *
	 * @return A negated version of this {@link Predicate5}
	 */
	default Predicate5<A, B, C, D, E> negate(){
		return (a, b, c, d, e) -> !this.test(a, b, c, d, e);
	}
}
