package com.github.tadukoo.util.functional.function;

/**
 * A function that takes eight arguments, returns a result,
 * and may throw a {@link Throwable}.
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
 * @param <T> The type of {@link Throwable} thrown by the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingFunction8<A, B, C, D, E, F, G, H, R, T extends Throwable>{
	
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
	 * @throws T Determined by the function, not required
	 */
	R apply(A a, B b, C c, D d, E e, F f, G g, H h) throws T;
	
	/**
	 * Creates a ThrowingFunction8 that runs this ThrowingFunction8 and
	 * puts the result into the given {@link ThrowingFunction}.
	 * 
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param after A {@link ThrowingFunction} to put the result of this ThrowingFunction8 into
	 * @return The ThrowingFunction8 made from composing this one and the given {@link ThrowingFunction}
	 */
	default <S> ThrowingFunction8<A, B, C, D, E, F, G, H, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d, e, f, g, h) -> after.apply(this.apply(a, b, c, d, e, f, g, h));
	}
}
