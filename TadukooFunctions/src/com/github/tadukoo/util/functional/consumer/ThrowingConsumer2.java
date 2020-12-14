package com.github.tadukoo.util.functional.consumer;

import java.util.function.BiConsumer;

/**
 * A better version of Java's {@link BiConsumer} interface that 
 * allows for the consumers to throw whatever {@link Throwable}
 * is specified.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer2<A, B, T extends Throwable>{
	
	/**
	 * Takes two arguments and consumes them.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b) throws T;
	
	/**
	 * Creates a ThrowingConsumer2 that runs this ThrowingConsumer2 and then also runs the 
	 * given ThrowingConsumer2 on the same arguments.
	 * 
	 * @param after A 2nd ThrowingConsumer2 to run the arguments on after this one
	 * @return The ThrowingConsumer2 made from composing this one and the given one
	 */
	default ThrowingConsumer2<A, B, T> andThen(ThrowingConsumer2<? super A, ? super B, ? extends T> after){
		return (a, b) -> {
							this.accept(a, b);
							after.accept(a, b);
						};
	}
}
