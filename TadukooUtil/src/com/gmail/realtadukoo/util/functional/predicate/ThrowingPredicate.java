package com.gmail.realtadukoo.util.functional.predicate;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * A better version of Java's {@link Predicate} interface that 
 * allows for the predicates to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing predicates. 
 * See {@link ExceptionPredicate} for an example of a more 
 * fine-tuned extension.
 *
 * @param <S> The input argument type for the predicate
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingPredicate<S>{
	
	/**
	 * Takes a single argument and returns a boolean.
	 * 
	 * @param s The argument
	 * @return A boolean
	 * @throws Throwable
	 */
	public abstract boolean test(S s) throws Throwable;
	
	/**
	 * Creates a ThrowingPredicate that will test the argument with this ThrowingPredicate 
	 * and with the given ThrowingPredicate, returning true only if both results are true.
	 * 
	 * @param other The other ThrowingPredicate to test the argument on
	 * @return The ThrowingPredicate that results from composing this one and the given one
	 */
	public default ThrowingPredicate<S> and(ThrowingPredicate<? super S> other){
		return s -> this.test(s) && other.test(s);
	}
	
	/**
	 * Creates a ThrowingPredicate that will test the argument with this ThrowingPredicate 
	 * and with the given ThrowingPredicate, returning true if either result is true.
	 * 
	 * @param other The other ThrowingPredicate to test the argument on
	 * @return The ThrowingPredicate that results from composing this one and the given one
	 */
	public default ThrowingPredicate<S> or(ThrowingPredicate<? super S> other){
		return s -> this.test(s) || other.test(s);
	}
	
	/**
	 * Creates a ThrowingPredicate that will return the opposite result of this ThrowingPredicate.
	 * 
	 * @return A negated version of this ThrowingPredicate
	 */
	public default ThrowingPredicate<S> negate(){
		return s -> !this.test(s);
	}
	
	/**
	 * Creates a ThrowingPredicate that tests if two arguments are equal according 
	 * to Objects.equals(Object, Object).
	 * 
	 * @param <S> The argument type
	 * @param obj The object to test against
	 * @return A ThrowingPredicate that tests if two objects are equal
	 */
	public static <S> ThrowingPredicate<S> isEqual(Object obj){
		return s -> Objects.equals(obj, s);
	}
}
