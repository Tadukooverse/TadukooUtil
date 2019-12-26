package com.gmail.realtadukoo.util.functional.function;

/**
 * A Function that takes two arguments, returns a result, 
 * and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingFunction2} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The 1st input argument type for the function
 * @param <T> The 2nd input argument type for the function
 * @param <R> The output result type for the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionFunction2<S, T, R> extends ThrowingFunction2<S, T, R>{
	
	/**
	 * Takes two arguments and returns a result.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @return A result
	 * @throws Exception
	 */
	@Override
	public abstract R apply(S s, T t) throws Exception;
}
