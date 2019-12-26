package com.gmail.realtadukoo.util.functional.predicate;

import java.util.function.BiPredicate;

/**
 * A better version of Java's {@link BiPredicate} interface that 
 * allows for the predicates to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing predicates. 
 * See {@link ExceptionPredicate2} for an example of a more 
 * fine-tuned extension.
 *
 * @param <S> The input argument type for the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate2<S, T>{
	
	/**
	 * Takes two arguments and returns a boolean.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @return A boolean
	 * @throws Throwable
	 */
	public abstract boolean test(S s, T t) throws Throwable;
	
	/**
	 * Creates a ThrowingPredicate2 that will test the arguments with this ThrowingPredicate2 
	 * and with the given ThrowingPredicate2, returning true only if both results are true.
	 * 
	 * @param other The other ThrowingPredicate2 to test the arguments on
	 * @return The ThrowingPredicate2 that results from composing this one and the given one
	 */
	public default ThrowingPredicate2<S, T> and(ThrowingPredicate2<? super S, ? super T> other){
		return (s, t) -> this.test(s, t) && other.test(s, t);
	}
	
	/**
	 * Creates a ThrowingPredicate2 that will test the arguments with this ThrowingPredicate2 
	 * and with the given ThrowingPredicate2, returning true if either result is true.
	 * 
	 * @param other The other ThrowingPredicate2 to test the arguments on
	 * @return The ThrowingPredicate2 that results from composing this one and the given one
	 */
	public default ThrowingPredicate2<S, T> or(ThrowingPredicate2<? super S, ? super T> other){
		return (s, t) -> this.test(s, t) || other.test(s, t);
	}
	
	/**
	 * Creates a ThrowingPredicate2 that will return the opposite result of this ThrowingPredicate2.
	 * 
	 * @return A negated version of this ThrowingPredicate2
	 */
	public default ThrowingPredicate2<S, T> negate(){
		return (s, t) -> !this.test(s, t);
	}
}
