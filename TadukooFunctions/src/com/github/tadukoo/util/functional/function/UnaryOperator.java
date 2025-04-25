package com.github.tadukoo.util.functional.function;

/**
 * A {@link Function} that takes and returns the same type
 *
 * @param <A> The input and output argument type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface UnaryOperator<A> extends Function<A, A>{
	
	/**
	 * Takes a single argument and returns a result of the same type.
	 *
	 * @param a The argument
	 * @return A result
	 */
	A apply(A a);
	
	/**
	 * Returns a {@link UnaryOperator} that always returns its input argument
	 *
	 * @param <A> The type of argument
	 * @return A {@link UnaryOperator} that always returns its input argument
	 */
	static <A> UnaryOperator<A> identity(){
		return a -> a;
	}
}
