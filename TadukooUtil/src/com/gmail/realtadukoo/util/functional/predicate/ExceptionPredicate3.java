package com.gmail.realtadukoo.util.functional.predicate;

/**
 * A Predicate that takes three arguments, returns a boolean, 
 * and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingPredicate3} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The 1st input argument type for the predicate
 * @param <T> The 2nd input argument type for the predicate
 * @param <U> The 3rd input argument type for the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionPredicate3<S, T, U> extends ThrowingPredicate3<S, T, U>{
	
	/**
	 * Takes three arguments and returns a boolean.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @param u The 3rd argument
	 * @return A boolean
	 * @throws Exception
	 */
	public abstract boolean test(S s, T t, U u) throws Exception;
	
	/**
	 * Creates an ExceptionPredicate3 that will test the arguments with this ExceptionPredicate3 
	 * and with the given ExceptionPredicate3, returning true only if both results are true.
	 * 
	 * @param other The other ExceptionPredicate3 to test the arguments on
	 * @return The ExceptionPredicate3 that results from composing this one and the given one
	 */
	public default ExceptionPredicate3<S, T, U> and(ExceptionPredicate3<? super S, ? super T, ? super U> other){
		return (s, t, u) -> this.test(s, t, u) && other.test(s, t, u);
	}
	
	/**
	 * Creates an ExceptionPredicate3 that will test the arguments with this ExceptionPredicate3 
	 * and with the given ExceptionPredicate3, returning true if either result is true.
	 * 
	 * @param other The other ExceptionPredicate3 to test the arguments on
	 * @return The ExceptionPredicate3 that results from composing this one and the given one
	 */
	public default ExceptionPredicate3<S, T, U> or(ExceptionPredicate3<? super S, ? super T, ? super U> other){
		return (s, t, u) -> this.test(s, t, u) || other.test(s, t, u);
	}
	
	/**
	 * Creates an ExceptionPredicate3 that will return the opposite result of this ExceptionPredicate3.
	 * 
	 * @return A negated version of this ExceptionPredicate3
	 */
	public default ExceptionPredicate3<S, T, U> negate(){
		return (s, t, u) -> !this.test(s, t, u);
	}
}
