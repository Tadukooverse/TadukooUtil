package com.github.tadukoo.util.functional.function;

/**
 * A better version of Java's {@link java.util.function.Function} interface that
 * allows for the functions to throw whatever {@link Throwable} is
 * specified.
 *
 * @param <A> The input argument type for the function
 * @param <R> The output result type for the function
 * @param <T> The type of {@link Throwable} thrown by the function
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingFunction<A, R, T extends Throwable>{
	
	/**
	 * Takes a single argument and returns a result.
	 * 
	 * @param a The argument
	 * @return A result
	 * @throws T Determined by the function, not required
	 */
	R apply(A a) throws T;
	
	/**
	 * Creates a {@link ThrowingFunction} that runs the given {@link ThrowingFunction} and puts the result
	 * into this {@link ThrowingFunction}.
	 * 
	 * @param <S> The input type to the composed {@link ThrowingFunction}
	 * @param before The {@link ThrowingFunction} to run before this one, and put the result into this one
	 * @return The {@link ThrowingFunction} made from composing this one and the given one
	 */
	default <S> ThrowingFunction<S, R, T> compose(ThrowingFunction<? super S, ? extends A, ? extends T> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a {@link ThrowingFunction} that runs the given {@link Function} and puts the result
	 * into this {@link ThrowingFunction}.
	 *
	 * @param <S> The input type to the composed {@link ThrowingFunction}
	 * @param before The {@link Function} to run first and put the result into this {@link ThrowingFunction}
	 * @return The {@link ThrowingFunction} made from composing this {@link ThrowingFunction} and the given {@link Function}
	 */
	default <S> ThrowingFunction<S, R, T> compose(Function<? super S, ? extends A> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a {@link ThrowingFunction} that runs this {@link ThrowingFunction} and puts the result
	 * into the given {@link ThrowingFunction}.
	 * 
	 * @param <S> The output type of the 2nd {@link ThrowingFunction}
	 * @param after A 2nd {@link ThrowingFunction} to put the result of this one into
	 * @return The {@link ThrowingFunction} made from composing this one and the given one
	 */
	default <S> ThrowingFunction<A, S, T> andThen(ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return a -> after.apply(this.apply(a));
	}
	
	/**
	 * Creates a {@link ThrowingFunction} that runs this {@link ThrowingFunction} and puts the result
	 * into the given {@link Function}.
	 *
	 * @param <S> The output type of the {@link Function}
	 * @param after A {@link Function} to put the result of this {@link ThrowingFunction} into
	 * @return The {@link ThrowingFunction} made from composing this {@link ThrowingFunction} and the given {@link Function}
	 */
	default <S> ThrowingFunction<A, S, T> andThen(Function<? super R, ? extends S> after){
		return a -> after.apply(this.apply(a));
	}
	
	/**
	 * Returns a {@link ThrowingFunction} that always returns its input argument
	 * 
	 * @param <A> The type of argument
	 * @param <T> The {@link Throwable} being thrown
	 * @return A {@link ThrowingFunction} that always returns its input argument
	 */
	static <A, T extends Throwable> ThrowingFunction<A, A, T> identity(){
		return a -> a;
	}
}
