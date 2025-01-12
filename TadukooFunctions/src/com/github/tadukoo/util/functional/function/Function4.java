package com.github.tadukoo.util.functional.function;

/**
 * A function that takes four arguments and returns a result.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <D> The 4th input argument type for the function
 * @param <R> The output result type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Function4<A, B, C, D, R>{
	
	/**
	 * Takes four arguments and returns a result.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @return A result
	 */
	R apply(A a, B b, C c, D d);
	
	/**
	 * Creates a {@link Function4} that runs this {@link Function4} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link Function4} into
	 * @return The {@link Function4} made from composing this {@link Function4} and the given {@link Function}
	 */
	default <S> Function4<A, B, C, D, S> andThen(Function<? super R, ? extends S> after){
		return (a, b, c, d) -> after.apply(this.apply(a, b, c, d));
	}
	
	/**
	 * Creates a {@link ThrowingFunction4} that runs this {@link Function4} and
	 * puts the result into the given {@link ThrowingFunction}.
	 *
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param after A {@link ThrowingFunction} to put the result of this {@link Function4} into
	 * @return The {@link ThrowingFunction4} made from composing this {@link Function4} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction4<A, B, C, D, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d) -> after.apply(this.apply(a, b, c, d));
	}
}
