package com.gmail.realtadukoo.util.functional.function;

/**
 * A function that takes four arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type for the function
 * @param <B> The 2nd input argument type for the function
 * @param <C> The 3rd input argument type for the function
 * @param <D> The 4th input argument type for the function
 * @param <R> The output result type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction4<A, B, C, D, R, T extends Throwable>{
	
	/**
	 * Takes four arguments and returns a result.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @return A result
	 * @throws T Determined by the function, not required
	 */
	R apply(A a, B b, C c, D d) throws T;
	
	/**
	 * Creates a ThrowingFunction3 that runs this ThrowingFunction4 and
	 * puts the result into the given {@link ThrowingFunction}.
	 * 
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param after A {@link ThrowingFunction} to put the result of this ThrowingFunction4 into
	 * @return The ThrowingFunction4 made from composing this one and the given {@link ThrowingFunction}
	 */
	default <S> ThrowingFunction4<A, B, C, D, S, T> andThen(ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return (a, b, c, d) -> after.apply(this.apply(a, b, c, d));
	}
}
