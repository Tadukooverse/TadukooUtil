package com.gmail.realtadukoo.util.functional.function;

/**
 * A Function that takes three arguments, returns a result, 
 * and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingFunction3} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The 1st input argument type for the function
 * @param <T> The 2nd input argument type for the function
 * @param <U> The 3rd input argument type for the function
 * @param <R> The output result type for the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionFunction3<S, T, U, R> extends ThrowingFunction3<S, T, U, R>{
	
	/**
	 * Takes three arguments and returns a result.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @param u The 3rd argument
	 * @return A result
	 * @throws Exception
	 */
	@Override
	public abstract R apply(S s, T t, U u) throws Exception;
	
	/**
	 * Creates an ExceptionFunction3 that runs this ExceptionFunction3 and 
	 * puts the result into the given {@link ExceptionFunction}.
	 * 
	 * @param <V> The output type of the {@link ExceptionFunction}
	 * @param after An {@link ExceptionFunction} to put the result of this ExceptionFunction3 into
	 * @return The ExceptionFunction3 made from composing this one and the given {@link ExceptionFunction}
	 */
	public default <V> ExceptionFunction3<S, T, U, V> andThen(ExceptionFunction<? super R, ? extends V> after){
		return (s, t, u) -> after.apply(this.apply(s, t, u));
	}
}
