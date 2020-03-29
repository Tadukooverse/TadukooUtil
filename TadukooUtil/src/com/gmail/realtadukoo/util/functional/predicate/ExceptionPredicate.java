package com.gmail.realtadukoo.util.functional.predicate;

import java.util.Objects;

/**
 * A Predicate that takes a single argument, returns a boolean, 
 * and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingPredicate} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The input argument type for the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionPredicate<S> extends ThrowingPredicate<S>{
	
	/**
	 * Takes a single argument and returns a boolean.
	 * 
	 * @param s The argument
	 * @return A boolean
	 * @throws Exception
	 */
	boolean test(S s) throws Exception;
	
	/**
	 * Creates an ExceptionPredicate that will test the argument with this ExceptionPredicate 
	 * and with the given ExceptionPredicate, returning true only if both results are true.
	 * 
	 * @param other The other ExceptionPredicate to test the argument on
	 * @return The ExceptionPredicate that results from composing this one and the given one
	 */
	default ExceptionPredicate<S> and(ExceptionPredicate<? super S> other){
		return s -> this.test(s) && other.test(s);
	}
	
	/**
	 * Creates an ExceptionPredicate that will test the argument with this ExceptionPredicate 
	 * and with the given ExceptionPredicate, returning true if either result is true.
	 * 
	 * @param other The other ExceptionPredicate to test the argument on
	 * @return The ExceptionPredicate that results from composing this one and the given one
	 */
	default ExceptionPredicate<S> or(ExceptionPredicate<? super S> other){
		return s -> this.test(s) || other.test(s);
	}
	
	/**
	 * Creates an ExceptionPredicate that will return the opposite result of this ExceptionPredicate.
	 * 
	 * @return A negated version of this ExceptionPredicate
	 */
	default ExceptionPredicate<S> negate(){
		return s -> !this.test(s);
	}
	
	/**
	 * Creates an ExceptionPredicate that tests if two arguments are equal according 
	 * to Objects.equals(Object, Object).
	 * 
	 * @param <S> The argument type
	 * @param obj The object to test against
	 * @return An ExceptionPredicate that tests if two objects are equal
	 */
	static <S> ExceptionPredicate<S> isEqual(Object obj){
		return s -> Objects.equals(obj, s);
	}
}
