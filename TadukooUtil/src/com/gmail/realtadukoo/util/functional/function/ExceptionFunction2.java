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
	 * @throws Exception Determined by the function, not required
	 */
	@Override
	R apply(S s, T t) throws Exception;
	
	/**
	 * Creates an ExceptionFunction2 that runs this ExceptionFunction2 and 
	 * puts the result into the given {@link ExceptionFunction}.
	 * 
	 * @param <V> The output type of the {@link ExceptionFunction}
	 * @param after An {@link ExceptionFunction} to put the result of this ExceptionFunction2 into
	 * @return The ExceptionFunction2 made from composing this one and the given {@link ExceptionFunction}
	 */
	default <V> ExceptionFunction2<S, T, V> andThen(ExceptionFunction<? super R, ? extends V> after){
		return (s, t) -> after.apply(this.apply(s, t));
	}
}
