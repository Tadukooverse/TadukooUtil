package com.github.tadukoo.util.functional.function;

/**
 * A function that takes six arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <D> The 4th input argument type for the function
 * @param <E> The 5th input argument type for the function
 * @param <F> The 6th input argument type for the function
 * @param <R> The output result type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingFunction6<A, B, C, D, E, F, R, T extends Throwable>{
	
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
	 * @throws T Determined by the function, not required
	 */
	R apply(A a, B b, C c, D d, E e, F f) throws T;
	
	/**
	 * Creates a {@link ThrowingFunction6} that runs this {@link ThrowingFunction6} and
	 * puts the result into the given {@link ThrowingFunction}.
	 * 
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param after A {@link ThrowingFunction} to put the result of this {@link ThrowingFunction6} into
	 * @return The {@link ThrowingFunction6} made from composing this {@link ThrowingFunction6} and the given {@link ThrowingFunction}
	 */
	default <S> ThrowingFunction6<A, B, C, D, E, F, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d, e, f) -> after.apply(this.apply(a, b, c, d, e, f));
	}
	
	/**
	 * Creates a {@link ThrowingFunction6} that runs this {@link ThrowingFunction6} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link ThrowingFunction6} into
	 * @return The {@link ThrowingFunction6} made from composing this {@link ThrowingFunction6} and the given {@link Function}
	 */
	default <S> ThrowingFunction6<A, B, C, D, E, F, S, T> andThen(Function<? super R, ? extends S> after){
		return (a, b, c, d, e, f) -> after.apply(this.apply(a, b, c, d, e, f));
	}
}
