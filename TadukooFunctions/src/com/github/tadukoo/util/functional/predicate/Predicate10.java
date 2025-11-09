package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes ten arguments and returns a boolean.
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
 * @param <J> The 10th input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate10<A, B, C, D, E, F, G, H, I, J>{
	
	/**
	 * Takes ten arguments and returns a boolean.
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
	 * @param j The 10th argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j);
	
	/**
	 * Creates a {@link Predicate10} that will test the arguments with this {@link Predicate10}
	 * and with the given {@link Predicate10}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate10} to test the arguments on
	 * @return The {@link Predicate10} that results from composing this one and the given one
	 */
	default Predicate10<A, B, C, D, E, F, G, H, I, J> and(
			Predicate10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I, ? super J> other){
		return (a, b, c, d, e, f, g, h, i, j) -> this.test(a, b, c, d, e, f, g, h, i, j) &&
				other.test(a, b, c, d, e, f, g, h, i, j);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate10} that will test the arguments with this {@link Predicate10}
	 * and with the given {@link ThrowingPredicate10}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate10} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate10} that results from composing this {@link Predicate10} and the given {@link ThrowingPredicate10}
	 */
	default <T extends Throwable> ThrowingPredicate10<A, B, C, D, E, F, G, H, I, J, T> and(
			ThrowingPredicate10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I, ? super J, ? extends T> other){
		return (a, b, c, d, e, f, g, h, i, j) -> this.test(a, b, c, d, e, f, g, h, i, j) &&
				other.test(a, b, c, d, e, f, g, h, i, j);
	}
	
	/**
	 * Creates a {@link Predicate10} that will test the arguments with this {@link Predicate10}
	 * and with the given {@link Predicate10}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate10} to test the arguments on
	 * @return The {@link Predicate10} that results from composing this one and the given one
	 */
	default Predicate10<A, B, C, D, E, F, G, H, I, J> or(
			Predicate10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I, ? super J> other){
		return (a, b, c, d, e, f, g, h, i, j) -> this.test(a, b, c, d, e, f, g, h, i, j) ||
				other.test(a, b, c, d, e, f, g, h, i, j);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate10} that will test the arguments with this {@link Predicate10}
	 * and with the given {@link ThrowingPredicate10}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate10} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate10} that results from composing this {@link Predicate10} and the given {@link ThrowingPredicate10}
	 */
	default <T extends Throwable> ThrowingPredicate10<A, B, C, D, E, F, G, H, I, J, T> or(
			ThrowingPredicate10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? super H, ? super I, ? super J, ? extends T> other){
		return (a, b, c, d, e, f, g, h, i, j) -> this.test(a, b, c, d, e, f, g, h, i, j) ||
				other.test(a, b, c, d, e, f, g, h, i, j);
	}
	
	/**
	 * Creates a {@link Predicate10} that will return the opposite result of this {@link Predicate10}.
	 *
	 * @return A negated version of this {@link Predicate10}
	 */
	default Predicate10<A, B, C, D, E, F, G, H, I, J> negate(){
		return (a, b, c, d, e, f, g, h, i, j) -> !this.test(a, b, c, d, e, f, g, h, i, j);
	}
}
