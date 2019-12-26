package com.gmail.realtadukoo.util.functional.function;

/**
 * A Function that takes a single argument, returns a result, 
 * and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingFunction} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The input argument type for the function
 * @param <R> The output result type for the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionFunction<S, R> extends ThrowingFunction<S, R>{
	
	/**
	 * Takes an argument and returns a result.
	 * 
	 * @param s The argument
	 * @return A result
	 * @throws Exception
	 */
	@Override
	public abstract R apply(S s) throws Exception;
}
