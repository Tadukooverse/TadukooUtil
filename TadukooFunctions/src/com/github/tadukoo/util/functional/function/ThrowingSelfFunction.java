package com.github.tadukoo.util.functional.function;

/**
 * A {@link ThrowingFunction} that takes and returns the same type
 *
 * @param <A> The input and output argument type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.6
 */
@FunctionalInterface
public interface ThrowingSelfFunction<A, T extends Throwable> extends ThrowingFunction<A, A, T>{
	
	/**
	 * Takes a single argument and returns a result of the same type.
	 *
	 * @param a The argument
	 * @return A result
	 * @throws T Determined by the function, not required
	 */
	A apply(A a) throws T;
	
	/**
	 * Creates a ThrowingFunction that runs the given ThrowingFunction and puts the result
	 * into this ThrowingSelfFunction.
	 *
	 * @param <S> The input type to the composed ThrowingFunction
	 * @param before The ThrowingFunction to run before this one, and put the result into this one
	 * @return The ThrowingFunction made from composing this one and the given one
	 */
	default <S> ThrowingFunction<S, A, T> compose(ThrowingFunction<? super S, ? extends A, ? extends T> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a ThrowingFunction that runs this ThrowingSelfFunction and puts the result
	 * into the given ThrowingFunction.
	 *
	 * @param <S> The output type of the 2nd ThrowingFunction
	 * @param after A 2nd ThrowingFunction to put the result of this one into
	 * @return The ThrowingFunction made from composing this one and the given one
	 */
	default <S> ThrowingFunction<A, S, T> andThen(ThrowingFunction<? super A, ? extends S, ? extends T> after){
		return a -> after.apply(this.apply(a));
	}
	
	/**
	 * Returns a ThrowingSelfFunction that always returns its input argument
	 *
	 * @param <A> The type of argument
	 * @param <T> The {@link Throwable} being thrown
	 * @return A ThrowingFunction that always returns its input argument
	 */
	static <A, T extends Throwable> ThrowingSelfFunction<A, T> identity(){
		return a -> a;
	}
}
