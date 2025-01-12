package com.github.tadukoo.util.functional.function;

/**
 * A function that takes six arguments and returns a result.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <D> The 4th input argument type for the function
 * @param <E> The 5th input argument type for the function
 * @param <F> The 6th input argument type for the function
 * @param <R> The output result type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Function6<A, B, C, D, E, F, R>{
	
	/**
	 * Takes six arguments and returns a result.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @return A result
	 */
	R apply(A a, B b, C c, D d, E e, F f);
	
	/**
	 * Creates a {@link Function6} that runs this {@link Function6} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link Function6} into
	 * @return The {@link Function6} made from composing this {@link Function6} and the given {@link Function}
	 */
	default <S> Function6<A, B, C, D, E, F, S> andThen(Function<? super R, ? extends S> after){
		return (a, b, c, d, e, f) -> after.apply(this.apply(a, b, c, d, e, f));
	}
	
	/**
	 * Creates a {@link ThrowingFunction6} that runs this {@link Function6} and
	 * puts the result into the given {@link ThrowingFunction}.
	 *
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param after A {@link ThrowingFunction} to put the result of this {@link Function6} into
	 * @return The {@link ThrowingFunction6} made from composing this {@link Function6} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction6<A, B, C, D, E, F, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d, e, f) -> after.apply(this.apply(a, b, c, d, e, f));
	}
}
