package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes nine arguments and returns a boolean.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <E> The 5th input argument type for the predicate
 * @param <F> The 6th input argument type for the predicate
 * @param <G> The 7th input argument type for the predicate
 * @param <H> The 8th input argument type for the predicate
 * @param <I> The 9th input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate9<A, B, C, D, E, F, G, H, I>{
	
	/**
	 * Takes nine arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @param h The 8th argument
	 * @param i The 9th argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c, D d, E e, F f, G g, H h, I i);
	
	/**
	 * Creates a {@link Predicate9} that will test the arguments with this {@link Predicate9}
	 * and with the given {@link Predicate9}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate9} to test the arguments on
	 * @return The {@link Predicate9} that results from composing this one and the given one
	 */
	default Predicate9<A, B, C, D, E, F, G, H, I> and(
			Predicate9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I> other){
		return (a, b, c, d, e, f, g, h, i) -> this.test(a, b, c, d, e, f, g, h, i) &&
				other.test(a, b, c, d, e, f, g, h, i);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate9} that will test the arguments with this {@link Predicate9}
	 * and with the given {@link ThrowingPredicate9}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate9} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate9} that results from composing this {@link Predicate9} and the given {@link ThrowingPredicate9}
	 */
	default <T extends Throwable> ThrowingPredicate9<A, B, C, D, E, F, G, H, I, T> and(
			ThrowingPredicate9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I, ? extends T> other){
		return (a, b, c, d, e, f, g, h, i) -> this.test(a, b, c, d, e, f, g, h, i) &&
				other.test(a, b, c, d, e, f, g, h, i);
	}
	
	/**
	 * Creates a {@link Predicate9} that will test the arguments with this {@link Predicate9}
	 * and with the given {@link Predicate9}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate9} to test the arguments on
	 * @return The {@link Predicate9} that results from composing this one and the given one
	 */
	default Predicate9<A, B, C, D, E, F, G, H, I> or(
			Predicate9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I> other){
		return (a, b, c, d, e, f, g, h, i) -> this.test(a, b, c, d, e, f, g, h, i) ||
				other.test(a, b, c, d, e, f, g, h, i);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate9} that will test the arguments with this {@link Predicate9}
	 * and with the given {@link ThrowingPredicate9}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate9} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate9} that results from composing this {@link Predicate9} and the given {@link ThrowingPredicate9}
	 */
	default <T extends Throwable> ThrowingPredicate9<A, B, C, D, E, F, G, H, I, T> or(
			ThrowingPredicate9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I, ? extends T> other){
		return (a, b, c, d, e, f, g, h, i) -> this.test(a, b, c, d, e, f, g, h, i) ||
				other.test(a, b, c, d, e, f, g, h, i);
	}
	
	/**
	 * Creates a {@link Predicate9} that will return the opposite result of this {@link Predicate9}.
	 *
	 * @return A negated version of this {@link Predicate9}
	 */
	default Predicate9<A, B, C, D, E, F, G, H, I> negate(){
		return (a, b, c, d, e, f, g, h, i) -> !this.test(a, b, c, d, e, f, g, h, i);
	}
}
