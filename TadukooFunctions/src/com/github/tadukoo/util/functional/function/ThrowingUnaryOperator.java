package com.github.tadukoo.util.functional.function;

/**
 * A {@link ThrowingFunction} that takes and returns the same type
 *
 * @param <A> The input and output argument type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since Beta v.0.6
 */
@FunctionalInterface
public interface ThrowingUnaryOperator<A, T extends Throwable> extends ThrowingFunction<A, A, T>{
	
	/**
	 * Takes a single argument and returns a result of the same type.
	 *
	 * @param a The argument
	 * @return A result
	 * @throws T Determined by the function, not required
	 */
	A apply(A a) throws T;
	
	/**
	 * Returns a {@link ThrowingUnaryOperator} that always returns its input argument
	 *
	 * @param <A> The type of argument
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingUnaryOperator} that always returns its input argument
	 */
	static <A, T extends Throwable> ThrowingUnaryOperator<A, T> identity(){
		return a -> a;
	}
}
