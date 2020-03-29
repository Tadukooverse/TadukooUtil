package com.gmail.realtadukoo.util.functional.function;

import java.util.function.BiFunction;

/**
 * A better version of Java's {@link BiFunction} interface that 
 * allows for the functions to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing functions. 
 * See {@link ExceptionFunction2} as an example of a more
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
	R apply(S s, T t) throws Throwable;
	
	/**
	 * Creates a ThrowingFunction2 that runs this ThrowingFunction2 and 
	 * puts the result into the given {@link ThrowingFunction}.
	 * 
	 * @param <V> The output type of the {@link ThrowingFunction}
	 * @param after A {@link ThrowingFunction} to put the result of this ThrowingFunction2 into
	 * @return The ThrowingFunction2 made from composing this one and the given {@link ThrowingFunction}
	 */
	default <V> ThrowingFunction2<S, T, V> andThen(ThrowingFunction<? super R, ? extends V> after){
		return (s, t) -> after.apply(this.apply(s, t));
	}
}
