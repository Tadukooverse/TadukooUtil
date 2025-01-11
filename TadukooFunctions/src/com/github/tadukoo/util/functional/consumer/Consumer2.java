package com.github.tadukoo.util.functional.consumer;

import java.util.function.BiConsumer;

/**
 * A better version of Java's {@link BiConsumer} interface that
 * works better with the throwing functional interfaces.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer2<A, B>{
	
	/**
	 * Takes two arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 */
	void accept(A a, B b);
	
	/**
	 * Creates a {@link Consumer2} that runs this {@link Consumer2} and then also runs the
	 * given {@link Consumer2} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer2} to run the arguments on after this one
	 * @return The {@link Consumer2} made from composing this one and the given one
	 */
	default Consumer2<A, B> andThen(Consumer2<? super A, ? super B> after){
		return (a, b) -> {
			this.accept(a, b);
			after.accept(a, b);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer2} that runs this {@link Consumer2} and then also runs the
	 * given {@link ThrowingConsumer2} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer2} to run the arguments on after this {@link Consumer2}
	 * @return The {@link ThrowingConsumer2} made from composing this {@link Consumer2} and the given {@link ThrowingConsumer2}
	 */
	default <T extends Throwable> ThrowingConsumer2<A, B, T> andThen(
			ThrowingConsumer2<? super A, ? super B, ? extends T> after){
		return (a, b) -> {
			this.accept(a, b);
			after.accept(a, b);
		};
	}
}
