package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes seven arguments and returns a boolean.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <E> The 5th input argument type for the predicate
 * @param <F> The 6th input argument type for the predicate
 * @param <G> The 7th input argument type for the predicate
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Predicate7<A, B, C, D, E, F, G>{
	
	/**
	 * Takes seven arguments and returns a boolean.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @return A boolean
	 */
	boolean test(A a, B b, C c, D d, E e, F f, G g);
	
	/**
	 * Creates a {@link Predicate7} that will test the arguments with this {@link Predicate7}
	 * and with the given {@link Predicate7}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate7} to test the arguments on
	 * @return The {@link Predicate7} that results from composing this one and the given one
	 */
	default Predicate7<A, B, C, D, E, F, G> and(
			Predicate7<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G> other){
		return (a, b, c, d, e, f, g) -> this.test(a, b, c, d, e, f, g) && other.test(a, b, c, d, e, f, g);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate7} that will test the arguments with this {@link Predicate7}
	 * and with the given {@link ThrowingPredicate7}, returning true only if both results are true.
	 *
	 * @param other The other {@link ThrowingPredicate7} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate7} that results from composing this {@link Predicate7} and the given {@link ThrowingPredicate7}
	 */
	default <T extends Throwable> ThrowingPredicate7<A, B, C, D, E, F, G, T> and(
			ThrowingPredicate7<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? extends T> other){
		return (a, b, c, d, e, f, g) -> this.test(a, b, c, d, e, f, g) && other.test(a, b, c, d, e, f, g);
	}
	
	/**
	 * Creates a {@link Predicate7} that will test the arguments with this {@link Predicate7}
	 * and with the given {@link Predicate7}, returning true if either result is true.
	 *
	 * @param other The other {@link Predicate7} to test the arguments on
	 * @return The {@link Predicate7} that results from composing this one and the given one
	 */
	default Predicate7<A, B, C, D, E, F, G> or(
			Predicate7<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G> other){
		return (a, b, c, d, e, f, g) -> this.test(a, b, c, d, e, f, g) || other.test(a, b, c, d, e, f, g);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate7} that will test the arguments with this {@link Predicate7}
	 * and with the given {@link ThrowingPredicate7}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate7} to test the arguments on
	 * @param <T> The {@link Throwable} being thrown
	 * @return The {@link ThrowingPredicate7} that results from composing this {@link Predicate7} and the given {@link ThrowingPredicate7}
	 */
	default <T extends Throwable> ThrowingPredicate7<A, B, C, D, E, F, G, T> or(
			ThrowingPredicate7<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? extends T> other){
		return (a, b, c, d, e, f, g) -> this.test(a, b, c, d, e, f, g) || other.test(a, b, c, d, e, f, g);
	}
	
	/**
	 * Creates a {@link Predicate7} that will return the opposite result of this {@link Predicate7}.
	 *
	 * @return A negated version of this {@link Predicate7}
	 */
	default Predicate7<A, B, C, D, E, F, G> negate(){
		return (a, b, c, d, e, f, g) -> !this.test(a, b, c, d, e, f, g);
	}
}
