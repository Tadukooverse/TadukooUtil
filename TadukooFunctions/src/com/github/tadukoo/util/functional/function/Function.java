package com.github.tadukoo.util.functional.function;

/**
 * A better version of Java's {@link java.util.function.Function} interface that
 * works better with the throwing functional interfaces.
 *
 * @param <A> The input argument type for the function
 * @param <R> The output result type for the function
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Function<A, R>{
	
	/**
	 * Takes a single argument and returns a result.
	 *
	 * @param a The argument
	 * @return A result
	 */
	R apply(A a);
	
	/**
	 * Creates a {@link Function} that runs the given {@link Function} and puts the result
	 * into this {@link Function}.
	 *
	 * @param <S> The input type to the composed {@link Function}
	 * @param before The {@link Function} to run before this one, and put the result into this one
	 * @return The {@link Function} made from composing this one and the given one
	 */
	default <S> Function<S, R> compose(Function<? super S, ? extends A> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a {@link ThrowingFunction} that runs the given {@link ThrowingFunction} and puts the result
	 * into this {@link Function}.
	 *
	 * @param <S> The input type to the composed {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param before The {@link ThrowingFunction} to run first and put the result into this {@link Function}
	 * @return The {@link ThrowingFunction} made from composing this {@link Function} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction<S, R, T> compose(
			ThrowingFunction<? super S, ? extends A, ? extends T> before){
		return v -> this.apply(before.apply(v));
	}
	
	/**
	 * Creates a {@link Function} that runs this {@link Function} and puts the result
	 * into the given {@link Function}.
	 *
	 * @param <S> The output type of the 2nd {@link Function}
	 * @param after A 2nd {@link Function} to put the result of this one into
	 * @return The {@link Function} made from composing this one and the given one
	 */
	default <S> Function<A, S> andThen(Function<? super R, ? extends S> after){
		return a -> after.apply(this.apply(a));
	}
	
	/**
	 * Creates a {@link ThrowingFunction} that runs this {@link Function} and puts the result
	 * into the given {@link ThrowingFunction}.
	 *
	 * @param <S> The output type of the {@link ThrowingFunction}
	 * @param <T> The {@link Throwable} being thrown
	 * @param after A {@link ThrowingFunction} to put the result of this {@link Function} into
	 * @return The {@link ThrowingFunction} made from composing this {@link Function} and the given {@link ThrowingFunction}
	 */
	default <S, T extends Throwable> ThrowingFunction<A, S, T> andThen(
			ThrowingFunction<? super R, ? extends S, ? extends T> after){
		return a -> after.apply(this.apply(a));
	}
	
	/**
	 * Returns a {@link Function} that always returns its input argument
	 *
	 * @param <A> The type of argument
	 * @return A {@link Function} that always returns its input argument
	 */
	static <A> Function<A, A> identity(){
		return a -> a;
	}
}
