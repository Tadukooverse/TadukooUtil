package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes eight arguments, returns a boolean,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <E> The 5th input argument type for the predicate
 * @param <F> The 6th input argument type for the predicate
 * @param <G> The 7th input argument type for the predicate
 * @param <H> The 8th input argument type for the predicate
 * @param <T> The type of {@link Throwable} thrown by the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingPredicate8<A, B, C, D, E, F, G, H, T extends Throwable>{
	
	/**
	 * Takes eight arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @param h The 8th argument
	 * @return A boolean
	 * @throws T Determined by the predicate, not required
	 */
	boolean test(A a, B b, C c, D d, E e, F f, G g, H h) throws T;
	
	/**
	 * Creates a {@link ThrowingPredicate8} that will test the arguments with this {@link ThrowingPredicate8}
	 * and with the given {@link ThrowingPredicate8}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate8} to test the arguments on
	 * @return The {@link ThrowingPredicate8} that results from composing this one and the given one
	 */
	default ThrowingPredicate8<A, B, C, D, E, F, G, H, T> and(
			ThrowingPredicate8<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? extends T> other){
		return (a, b, c, d, e, f, g, h) -> this.test(a, b, c, d, e, f, g, h) && other.test(a, b, c, d, e, f, g, h);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate8} that will test the arguments with this {@link ThrowingPredicate8}
	 * and with the given {@link Predicate8}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate8} to test the arguments on
	 * @return The {@link ThrowingPredicate8} that results from composing this {@link ThrowingPredicate8} and the given {@link Predicate8}
	 */
	default ThrowingPredicate8<A, B, C, D, E, F, G, H, T> and(
			Predicate8<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H> other){
		return (a, b, c, d, e, f, g, h) -> this.test(a, b, c, d, e, f, g, h) && other.test(a, b, c, d, e, f, g, h);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate8} that will test the arguments with this {@link ThrowingPredicate8}
	 * and with the given {@link ThrowingPredicate8}, returning true if either result is true.
	 * 
	 * @param other The other {@link ThrowingPredicate8} to test the arguments on
	 * @return The {@link ThrowingPredicate8} that results from composing this one and the given one
	 */
	default ThrowingPredicate8<A, B, C, D, E, F, G, H, T> or(
			ThrowingPredicate8<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? extends T> other){
		return (a, b, c, d, e, f, g, h) -> this.test(a, b, c, d, e, f, g, h) || other.test(a, b, c, d, e, f, g, h);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate8} that will test the arguments with this {@link ThrowingPredicate8}
	 * and with the given {@link Predicate8}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate8} to test the arguments on
	 * @return The {@link ThrowingPredicate8} that results from composing this {@link ThrowingPredicate8} and the given {@link Predicate8}
	 */
	default ThrowingPredicate8<A, B, C, D, E, F, G, H, T> or(
			Predicate8<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H> other){
		return (a, b, c, d, e, f, g, h) -> this.test(a, b, c, d, e, f, g, h) || other.test(a, b, c, d, e, f, g, h);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate8} that will return the opposite result of this {@link ThrowingPredicate8}.
	 * 
	 * @return A negated version of this {@link ThrowingPredicate8}
	 */
	default ThrowingPredicate8<A, B, C, D, E, F, G, H, T> negate(){
		return (a, b, c, d, e, f, g, h) -> !this.test(a, b, c, d, e, f, g, h);
	}
}
