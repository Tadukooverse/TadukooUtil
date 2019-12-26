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
	 * @throws Throwable
	 */
	public abstract R apply(S s) throws Throwable;
}
