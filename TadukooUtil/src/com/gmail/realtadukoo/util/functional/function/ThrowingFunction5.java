package com.gmail.realtadukoo.util.functional.function;

/**
 * A function that takes five arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <D> The 4th input argument type for the function
 * @param <E> The 5th input argument type for the function
 * @param <R> The output result type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction5<A, B, C, D, E, R, T extends Throwable>{
	
	/**
	 * Takes five arguments and returns a result.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @return A result
	 * @throws T Determined by the function, not required
	 */
	R apply(A a, B b, C c, D d, E e) throws T;
	
	/**
	 * Creates a ThrowingFunction5 that runs this ThrowingFunction5 and
	 * puts the result into the given {@link ThrowingFunction}.
	 * 
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param after A {@link ThrowingFunction} to put the result of this ThrowingFunction5 into
	 * @return The ThrowingFunction5 made from composing this one and the given {@link ThrowingFunction}
	 */
	default <S> ThrowingFunction5<A, B, C, D, E, S, T> andThen(ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d, e) -> after.apply(this.apply(a, b, c, d, e));
	}
}
