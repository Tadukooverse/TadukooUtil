package com.github.tadukoo.util.functional.predicate;

/**
 * A predicate that takes four arguments, returns a boolean,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <D> The 4th input argument type for the predicate
 * @param <T> The type of {@link Throwable} thrown by the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate4<A, B, C, D, T extends Throwable>{
	
	/**
	 * Takes four arguments and returns a boolean.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @return A boolean
	 * @throws T Determined by the predicate, not required
	 */
	boolean test(A a, B b, C c, D d) throws T;
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will test the arguments with this {@link ThrowingPredicate4}
	 * and with the given {@link ThrowingPredicate4}, returning true only if both results are true.
	 * 
	 * @param other The other {@link ThrowingPredicate4} to test the arguments on
	 * @return The {@link ThrowingPredicate4} that results from composing this one and the given one
	 */
	default ThrowingPredicate4<A, B, C, D, T> and(
			ThrowingPredicate4<? super A, ? super B, ? super C, ? super D, ? extends T> other){
		return (a, b, c, d) -> this.test(a, b, c, d) && other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will test the arguments with this {@link ThrowingPredicate4}
	 * and with the given {@link Predicate4}, returning true only if both results are true.
	 *
	 * @param other The other {@link Predicate4} to test the arguments on
	 * @return The {@link ThrowingPredicate4} that results from composing this {@link ThrowingPredicate4} and the given {@link Predicate4}
	 */
	default ThrowingPredicate4<A, B, C, D, T> and(Predicate4<? super A, ? super B, ? super C, ? super D> other){
		return (a, b, c, d) -> this.test(a, b, c, d) && other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will test the arguments with this {@link ThrowingPredicate4}
	 * and with the given {@link ThrowingPredicate4}, returning true if either result is true.
	 * 
	 * @param other The other {@link ThrowingPredicate4} to test the arguments on
	 * @return The {@link ThrowingPredicate4} that results from composing this one and the given one
	 */
	default ThrowingPredicate4<A, B, C, D, T> or(
			ThrowingPredicate4<? super A, ? super B, ? super C, ? super D, ? extends T> other){
		return (a, b, c, d) -> this.test(a, b, c, d) || other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will test the arguments with this {@link ThrowingPredicate4}
	 * and with the given {@link Predicate4}, returning true if either result is true.
	 *
	 * @param other The other {@link ThrowingPredicate4} to test the arguments on
	 * @return The {@link ThrowingPredicate4} that results from composing this {@link ThrowingPredicate4} and the given {@link Predicate4}
	 */
	default ThrowingPredicate4<A, B, C, D, T> or(Predicate4<? super A, ? super B, ? super C, ? super D> other){
		return (a, b, c, d) -> this.test(a, b, c, d) || other.test(a, b, c, d);
	}
	
	/**
	 * Creates a {@link ThrowingPredicate4} that will return the opposite result of this {@link ThrowingPredicate4}.
	 * 
	 * @return A negated version of this {@link ThrowingPredicate4}
	 */
	default ThrowingPredicate4<A, B, C, D, T> negate(){
		return (a, b, c, d) -> !this.test(a, b, c, d);
	}
}
