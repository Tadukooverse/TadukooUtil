package com.github.tadukoo.util.functional.function;

import java.util.function.BiFunction;

/**
 * A better version of Java's {@link BiFunction} interface that 
 * allows for the functions to throw whatever {@link Throwable} is
 * specified.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <R> The output result type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction2<A, B, R, T extends Throwable>{
	
	/**
	 * Takes two arguments and returns a result.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @return R result
	 * @throws T Determined by the function, not required
	 */
	R apply(A a, B b) throws T;
	
	/**
	 * Creates a {@link ThrowingFunction2} that runs this {@link ThrowingFunction2} and
	 * puts the result into the given {@link ThrowingFunction}.
	 * 
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param after A {@link ThrowingFunction} to put the result of this {@link ThrowingFunction2} into
	 * @return The {@link ThrowingFunction2} made from composing this one and the given {@link ThrowingFunction}
	 */
	default <S> ThrowingFunction2<A, B, S, T> andThen(ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b) -> after.apply(this.apply(a, b));
	}
	
	/**
	 * Creates a {@link ThrowingFunction2} that runs this {@link ThrowingFunction2} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link ThrowingFunction2}into
	 * @return The {@link ThrowingFunction2} made from composing this one and the given {@link Function}
	 */
	default <S> ThrowingFunction2<A, B, S, T> andThen(Function<? super R, ? extends S> after){
		return (a, b) -> after.apply(this.apply(a, b));
	}
}
