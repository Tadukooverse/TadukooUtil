package com.gmail.realtadukoo.util.functional.function;

import java.util.function.Function;

/**
 * A better version of Java's {@link Function} interface that 
 * allows for the functions to throw whatever {@link Throwable} is
 * specified.
 *
 * @param <A> The input argument type for the function
 * @param <R> The output result type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction<A, R, T extends Throwable>{
	
	/**
	 * Takes a single argument and returns a result.
	 * 
	 * @param a The argument
	 * @return A result
	 * @throws T Determined by the function, not required
	 */
	R apply(A a) throws T;
	
	/**
	 * Creates a ThrowingFunction that runs the given ThrowingFunction and puts the result 
	 * into this ThrowingFunction.
	 * 
	 * @param <S> The input type to the composed ThrowingFunction
	 * @param before The ThrowingFunction to run before this one, and put the result into this one
	 * @return The ThrowingFunction made from composing this one and the given one
	 */
	default <S> ThrowingFunction<S, R, T> compose(ThrowingFunction<? super S, ? extends A, ? extends T> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a ThrowingFunction that runs this ThrowingFunction and puts the result 
	 * into the given ThrowingFunction.
	 * 
	 * @param <S> The output type of the 2nd ThrowingFunction
	 * @param after A 2nd ThrowingFunction to put the result of this one into
	 * @return The ThrowingFunction made from composing this one and the given one
	 */
	default <S> ThrowingFunction<A, S, T> andThen(ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return a -> after.apply(this.apply(a));
	}
	
	/**
	 * Returns a ThrowingFunction that always returns its input argument
	 * 
	 * @param <A> The type of argument
	 * @param <T> The {@link Throwable} being thrown
	 * @return A ThrowingFunction that always returns its input argument
	 */
	static <A, T extends Throwable> ThrowingFunction<A, A, T> identity(){
		return a -> a;
	}
}
