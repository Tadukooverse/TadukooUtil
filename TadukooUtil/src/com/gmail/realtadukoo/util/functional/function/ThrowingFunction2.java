package com.gmail.realtadukoo.util.functional.function;

import java.util.function.BiFunction;

/**
 * A better version of Java's {@link BiFunction} interface that 
 * allows for the functions to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing functions. 
 * See {@link ExceptionBiFunction} as an example of a more 
 * fine-tuned extension.
 *
 * @param <S> The 1st input argument type for the function
 * @param <T> The 2nd input argument type for the function
 * @param <R> The output result type for the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction2<S, T, R>{
	
	/**
	 * Takes two arguments and returns a result.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @return A result
	 * @throws Throwable
	 */
	public abstract R apply(S s, T t) throws Throwable;
}
