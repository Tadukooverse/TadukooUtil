package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes four arguments and returns a boolean.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate4<A, B, C, D>{
	
	/**
	 * Takes four arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c, D d);
	
	/**
	 * Creates a {@link Predicate4} that will test the arguments with this {@link Predicate4}
	 * and with the given {@link Predicate4}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate4} to test the arguments on
	 * @return The {@link Predicate4} that results from composing this one and the given one
	 */
	default Predicate4<A, B, C, D> and(Predicate4<? super A, ? super B, ? super C, ? super D> other){
		return (a, b, c, d) -> this.test(a, b, c, d) && other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will test the arguments with this {@link Predicate4}
	 * and with the given {@link ThrowingPredicate4}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate4} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate4} that results from composing this {@link Predicate4} and the given {@link ThrowingPredicate4}
	 */
	default <T extends Throwable> ThrowingPredicate4<A, B, C, D, T> and(
			ThrowingPredicate4<? super A, ? super B, ? super C, ? super D, ? extends T> other){
		return (a, b, c, d) -> this.test(a, b, c, d) && other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link Predicate4} that will test the arguments with this {@link Predicate4}
	 * and with the given {@link Predicate4}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate4} to test the arguments on
	 * @return The {@link Predicate4} that results from composing this one and the given one
	 */
	default Predicate4<A, B, C, D> or(Predicate4<? super A, ? super B, ? super C, ? super D> other){
		return (a, b, c, d) -> this.test(a, b, c, d) || other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will test the arguments with this {@link Predicate4}
	 * and with the given {@link ThrowingPredicate4}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate4} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate4} that results from composing this {@link Predicate4} and the given {@link ThrowingPredicate4}
	 */
	default <T extends Throwable> ThrowingPredicate4<A, B, C, D, T> or(
			ThrowingPredicate4<? super A, ? super B, ? super C, ? super D, ? extends T> other){
		return (a, b, c, d) -> this.test(a, b, c, d) || other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link Predicate4} that will return the opposite result of this {@link Predicate4}.
	 *
	 * @return A negated version of this {@link Predicate4}
	 */
	default Predicate4<A, B, C, D> negate(){
		return (a, b, c, d) -> !this.test(a, b, c, d);
	}
}
