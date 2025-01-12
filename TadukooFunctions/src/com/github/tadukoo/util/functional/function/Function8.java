package com.github.tadukoo.util.functional.function;

/**
 * A function that takes eight arguments and returns a result.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <D> The 4th input argument type for the function
 * @param <E> The 5th input argument type for the function
 * @param <F> The 6th input argument type for the function
 * @param <G> The 7th input argument type for the function
 * @param <H> The 8th input argument type for the function
 * @param <R> The output result type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Function8<A, B, C, D, E, F, G, H, R>{
	
	/**
	 * Takes eight arguments and returns a result.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @param h The 8th argument
	 * @return A result
	 */
	R apply(A a, B b, C c, D d, E e, F f, G g, H h);
	
	/**
	 * Creates a {@link Function8} that runs this {@link Function8} and
	 * puts the result into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link Function8} into
	 * @return The {@link Function8} made from composing this {@link Function8} and the given {@link Function}
	 */
	default <S> Function8<A, B, C, D, E, F, G, H, S> andThen(Function<? super R, ? extends S> after){
		return (a, b, c, d, e, f, g, h) -> after.apply(this.apply(a, b, c, d, e, f, g, h));
	}
	
	/**
	 * Creates a {@link ThrowingFunction8} that runs this {@link Function8} and
	 * puts the result into the given {@link ThrowingFunction}.
	 *
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param after A {@link ThrowingFunction} to put the result of this {@link Function8} into
	 * @return The {@link ThrowingFunction8} made from composing this {@link Function8} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction8<A, B, C, D, E, F, G, H, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d, e, f, g, h) -> after.apply(this.apply(a, b, c, d, e, f, g, h));
	}
}
