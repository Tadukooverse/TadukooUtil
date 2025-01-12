package com.github.tadukoo.util.functional.function;

import java.util.function.BiFunction;

/**
 * A better version of Java's {@link BiFunction} interface that
 * works better with the throwing functional interfaces.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <R> The output result type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Function2<A, B, R>{
	
	/**
	 * Takes two arguments and returns a result.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @return R result
	 */
	R apply(A a, B b);
	
	/**
	 * Creates a {@link Function2} that runs this {@link Function2} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link Function2} into
	 * @return The {@link Function2} made from composing this one and the given {@link Function}
	 */
	default <S> Function2<A, B, S> andThen(Function<? super R, ? extends S> after){
		return (a, b) -> after.apply(this.apply(a, b));
	}
	
	/**
	 * Creates a {@link ThrowingFunction2} that runs this {@link Function2} and
	 * puts the result into the given {@link ThrowingFunction}.
	 *
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param after A {@link ThrowingFunction} to put the result of this {@link Function2} into
	 * @return The {@link ThrowingFunction2} made from composing this {@link Function2} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction2<A, B, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b) -> after.apply(this.apply(a, b));
	}
}
