package com.gmail.realtadukoo.util.functional.predicate;

/**
 * A Predicate that takes two arguments, returns a boolean, 
 * and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingPredicate2} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The 1st input argument type for the predicate
 * @param <T> The 2nd input argument type for the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionPredicate2<S, T> extends ThrowingPredicate2<S, T>{
	
	/**
	 * Takes two arguments and returns a boolean.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @return A boolean
	 * @throws Exception
	 */
	public abstract boolean test(S s, T t) throws Exception;
	
	/**
	 * Creates an ExceptionPredicate2 that will test the arguments with this ExceptionPredicate2 
	 * and with the given ExceptionPredicate2, returning true only if both results are true.
	 * 
	 * @param other The other ExceptionPredicate2 to test the arguments on
	 * @return The ExceptionPredicate2 that results from composing this one and the given one
	 */
	public default ExceptionPredicate2<S, T> and(ExceptionPredicate2<? super S, ? super T> other){
		return (s, t) -> this.test(s, t) && other.test(s, t);
	}
	
	/**
	 * Creates an ExceptionPredicate2 that will test the arguments with this ExceptionPredicate2 
	 * and with the given ExceptionPredicate2, returning true if either result is true.
	 * 
	 * @param other The other ExceptionPredicate2 to test the arguments on
	 * @return The ExceptionPredicate2 that results from composing this one and the given one
	 */
	public default ExceptionPredicate2<S, T> or(ExceptionPredicate2<? super S, ? super T> other){
		return (s, t) -> this.test(s, t) || other.test(s, t);
	}
	
	/**
	 * Creates an ExceptionPredicate2 that will return the opposite result of this ExceptionPredicate2.
	 * 
	 * @return A negated version of this ExceptionPredicate2
	 */
	public default ExceptionPredicate2<S, T> negate(){
		return (s, t) -> !this.test(s, t);
	}
}
