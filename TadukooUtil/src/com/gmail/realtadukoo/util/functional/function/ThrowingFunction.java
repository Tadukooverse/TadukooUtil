package com.gmail.realtadukoo.util.functional.function;

import java.util.function.Function;

/**
 * A better version of Java's {@link Function} interface that 
 * allows for the functions to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing functions. 
 * See {@link ExceptionFunction} and SQLFunction (in Tadukoo Database) 
 * for examples of more fine-tuned extensions.
 *
 * @param <S> The input argument type for the function
 * @param <R> The output result type for the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction<S, R>{
	
	/**
	 * Takes a single argument and returns a result.
	 * 
	 * @param s The argument
	 * @return A result
	 * @throws Throwable Determined by the function, not required
	 */
	R apply(S s) throws Throwable;
	
	/**
	 * Creates a ThrowingFunction that runs the given ThrowingFunction and puts the result 
	 * into this ThrowingFunction.
	 * 
	 * @param <V> The input type to the composed ThrowingFunction
	 * @param before The ThrowingFunction to run before this one, and put the result into this one
	 * @return The ThrowingFunction made from composing this one and the given one
	 */
	default <V> ThrowingFunction<V, R> compose(ThrowingFunction<? super V, ? extends S> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a ThrowingFunction that runs this ThrowingFunction and puts the result 
	 * into the given ThrowingFunction.
	 * 
	 * @param <V> The output type of the 2nd ThrowingFunction
	 * @param after A 2nd ThrowingFunction to put the result of this one into
	 * @return The ThrowingFunction made from composing this one and the given one
	 */
	default <V> ThrowingFunction<S, V> andThen(ThrowingFunction<? super R, ? extends V> after){
		return s -> after.apply(this.apply(s));
	}
	
	/**
	 * Returns a ThrowingFunction that always returns its input argument
	 * 
	 * @param <S> The type of argument
	 * @return A ThrowingFunction that always returns its input argument
	 */
	static <S> ThrowingFunction<S, S> identity(){
		return s -> s;
	}
}
