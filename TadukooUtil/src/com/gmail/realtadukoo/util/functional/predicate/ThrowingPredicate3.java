package com.gmail.realtadukoo.util.functional.predicate;

/**
 * A predicate that takes three arguments, returns a boolean, 
 * and may throw a {@link Throwable}.
 *
 * @param <S> The 1st input argument type for the predicate
 * @param <T> The 2nd input argument type for the predicate
 * @param <U> The 3rd input argument type for the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate3<S, T, U>{
	
	/**
	 * Takes three arguments and returns a boolean.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @param u The 3rd argument
	 * @return A boolean
	 * @throws Throwable Determined by the predicate, not required
	 */
	boolean test(S s, T t, U u) throws Throwable;
	
	/**
	 * Creates a ThrowingPredicate3 that will test the arguments with this ThrowingPredicate3 
	 * and with the given ThrowingPredicate3, returning true only if both results are true.
	 * 
	 * @param other The other ThrowingPredicate3 to test the arguments on
	 * @return The ThrowingPredicate3 that results from composing this one and the given one
	 */
	default ThrowingPredicate3<S, T, U> and(ThrowingPredicate3<? super S, ? super T, ? super U> other){
		return (s, t, u) -> this.test(s, t, u) && other.test(s, t, u);
	}
	
	/**
	 * Creates a ThrowingPredicate3 that will test the arguments with this ThrowingPredicate3 
	 * and with the given ThrowingPredicate3, returning true if either result is true.
	 * 
	 * @param other The other ThrowingPredicate3 to test the arguments on
	 * @return The ThrowingPredicate3 that results from composing this one and the given one
	 */
	default ThrowingPredicate3<S, T, U> or(ThrowingPredicate3<? super S, ? super T, ? super U> other){
		return (s, t, u) -> this.test(s, t, u) || other.test(s, t, u);
	}
	
	/**
	 * Creates a ThrowingPredicate3 that will return the opposite result of this ThrowingPredicate3.
	 * 
	 * @return A negated version of this ThrowingPredicate3
	 */
	default ThrowingPredicate3<S, T, U> negate(){
		return (s, t, u) -> !this.test(s, t, u);
	}
}
