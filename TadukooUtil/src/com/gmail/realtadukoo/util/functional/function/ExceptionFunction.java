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
	
	/**
	 * Creates an ExceptionFunction that runs the given ExceptionFunction and puts the result 
	 * into this ExceptionFunction.
	 * 
	 * @param <V> The input type to the composed ExceptionFunction
	 * @param before The ExceptionFunction to run before this one, and put the result into this one
	 * @return The ExceptionFunction made from composing this one and the given one
	 */
	public default <V> ExceptionFunction<V, R> compose(ExceptionFunction<? super V, ? extends S> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates an ExceptionFunction that runs this ExceptionFunction and puts the result 
	 * into the given ExceptionFunction.
	 * 
	 * @param <V> The output type of the 2nd ExceptionFunction
	 * @param after A 2nd ExceptionFunction to put the result of this one into
	 * @return The ExceptionFunction made from composing this one and the given one
	 */
	public default <V> ExceptionFunction<S, V> andThen(ExceptionFunction<? super R, ? extends V> after){
		return s -> after.apply(this.apply(s));
	}
	
	/**
	 * Returns an ExceptionFunction that always returns its input argument
	 * 
	 * @param <S> The type of argument
	 * @return An ExceptionFunction that always returns its input argument
	 */
	public static <S> ExceptionFunction<S, S> identity(){
		return s -> {
						return s;
					};
	}
}
