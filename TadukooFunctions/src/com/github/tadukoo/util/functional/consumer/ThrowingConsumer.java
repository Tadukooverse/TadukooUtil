package com.github.tadukoo.util.functional.consumer;

import java.util.function.Consumer;

/**
 * A better version of Java's {@link Consumer} interface that 
 * allows for the consumers to throw whatever {@link Throwable}
 * is specified.
 *
 * @param <A> The input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer<A, T extends Throwable>{
	
	/**
	 * Takes a single argument and consumes it.
	 * 
	 * @param a The argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a) throws T;
	
	/**
	 * Creates a ThrowingConsumer that runs this ThrowingConsumer and then also runs the 
	 * given ThrowingConsumer on the same argument.
	 * 
	 * @param after A 2nd ThrowingConsumer to run the argument on after this one
	 * @return The ThrowingConsumer made from composing this one and the given one
	 */
	default ThrowingConsumer<A, T> andThen(ThrowingConsumer<? super A, ? extends T> after){
		return a -> {
						this.accept(a);
						after.accept(a);
					};
	}
}
