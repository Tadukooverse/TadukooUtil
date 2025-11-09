package com.github.tadukoo.util.functional.function;

/**
 * A function that takes three arguments and returns a result.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <R> The output result type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Function3<A, B, C, R>{
	
	/**
	 * Takes three arguments and returns a result.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @return A result
	 */
	R apply(A a, B b, C c);
	
	/**
	 * Creates a {@link Function3} that runs this {@link Function3} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link Function3} into
	 * @return The {@link Function3} made from composing this {@link Function3} and the given {@link Function}
	 */
	default <S> Function3<A, B, C, S> andThen(Function<? super R, ? extends S> after){
		return (a, b, c) -> after.apply(this.apply(a, b, c));
	}
	
	/**
	 * Creates a {@link ThrowingFunction3} that runs this {@link Function3} and
	 * puts the result into the given {@link ThrowingFunction}.
	 *
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param after A {@link ThrowingFunction} to put the result of this {@link Function3} into
	 * @return The {@link ThrowingFunction3} made from composing this {@link Function3} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction3<A, B, C, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c) -> after.apply(this.apply(a, b, c));
	}
}
