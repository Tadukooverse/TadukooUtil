package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes five arguments, returns a boolean,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <E> The 5th input argument type for the predicate
 * @param <T> The type of {@link Throwable} thrown by the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate5<A, B, C, D, E, T extends Throwable>{
	
	/**
	 * Takes five arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @return A boolean
	 * @throws T Determined by the predicate, not required
	 */
	boolean test(A a, B b, C c, D d, E e) throws T;
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will test the arguments with this {@link ThrowingPredicate5}
	 * and with the given {@link ThrowingPredicate5}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate5} to test the arguments on
	 * @return The {@link ThrowingPredicate5} that results from composing this one and the given one
	 */
	default ThrowingPredicate5<A, B, C, D, E, T> and(
			ThrowingPredicate5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends T> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) && other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will test the arguments with this {@link ThrowingPredicate5}
	 * and with the given {@link Predicate5}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate5} to test the arguments on
	 * @return The {@link ThrowingPredicate5} that results from composing this {@link ThrowingPredicate5} and the given {@link Predicate5}
	 */
	default ThrowingPredicate5<A, B, C, D, E, T> and(
			Predicate5<? super A, ? super B, ? super C, ? super D, ? super E> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) && other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will test the arguments with this {@link ThrowingPredicate5}
	 * and with the given {@link ThrowingPredicate5}, returning true if either result is true.
	 * 
	 * @param other The other {@link ThrowingPredicate5} to test the arguments on
	 * @return The {@link ThrowingPredicate5} that results from composing this one and the given one
	 */
	default ThrowingPredicate5<A, B, C, D, E, T> or(
			ThrowingPredicate5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends T> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) || other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will test the arguments with this {@link ThrowingPredicate5}
	 * and with the given {@link Predicate5}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate5} to test the arguments on
	 * @return The {@link ThrowingPredicate5} that results from composing this {@link ThrowingPredicate5} and the given {@link Predicate5}
	 */
	default ThrowingPredicate5<A, B, C, D, E, T> or(
			Predicate5<? super A, ? super B, ? super C, ? super D, ? super E> other){
		return (a, b, c, d, e) -> this.test(a, b, c, d, e) || other.test(a, b, c, d, e);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate5} that will return the opposite result of this {@link ThrowingPredicate5}.
	 * 
	 * @return A negated version of this {@link ThrowingPredicate5}
	 */
	default ThrowingPredicate5<A, B, C, D, E, T> negate(){
		return (a, b, c, d, e) -> !this.test(a, b, c, d, e);
	}
}
