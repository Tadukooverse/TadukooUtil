package com.gmail.realtadukoo.util.functional.predicate;

/**
 * A predicate that takes three arguments, returns a boolean, 
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the predicate
 * @param <B> The 2nd input argument type for the predicate
 * @param <C> The 3rd input argument type for the predicate
 * @param <T> The type of {@link Throwable} thrown by the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate3<A, B, C, T extends Throwable>{
	
	/**
	 * Takes three arguments and returns a boolean.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @return A boolean
	 * @throws T Determined by the predicate, not required
	 */
	boolean test(A a, B b, C c) throws T;
	
	/**
	 * Creates a ThrowingPredicate3 that will test the arguments with this ThrowingPredicate3 
	 * and with the given ThrowingPredicate3, returning true only if both results are true.
	 * 
	 * @param other The other ThrowingPredicate3 to test the arguments on
	 * @return The ThrowingPredicate3 that results from composing this one and the given one
	 */
	default ThrowingPredicate3<A, B, C, T> and(ThrowingPredicate3<? super A, ? super B, ? super C, ? extends T> other){
		return (a, b, c) -> this.test(a, b, c) && other.test(a, b, c);
	}
	
	/**
	 * Creates a ThrowingPredicate3 that will test the arguments with this ThrowingPredicate3 
	 * and with the given ThrowingPredicate3, returning true if either result is true.
	 * 
	 * @param other The other ThrowingPredicate3 to test the arguments on
	 * @return The ThrowingPredicate3 that results from composing this one and the given one
	 */
	default ThrowingPredicate3<A, B, C, T> or(ThrowingPredicate3<? super A, ? super B, ? super C, ? extends T> other){
		return (a, b, c) -> this.test(a, b, c) || other.test(a, b, c);
	}
	
	/**
	 * Creates a ThrowingPredicate3 that will return the opposite result of this ThrowingPredicate3.
	 * 
	 * @return A negated version of this ThrowingPredicate3
	 */
	default ThrowingPredicate3<A, B, C, T> negate(){
		return (a, b, c) -> !this.test(a, b, c);
	}
}
