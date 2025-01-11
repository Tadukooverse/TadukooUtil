package com.github.tadukoo.util.functional.consumer;

/**
 * A better version of Java's {@link java.util.function.Consumer} interface that
 * allows for the consumers to throw whatever {@link Throwable}
 * is specified.
 *
 * @param <A> The input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
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
	 * Creates a {@link ThrowingConsumer} that runs this {@link ThrowingConsumer} and then also runs the
	 * given {@link ThrowingConsumer} on the same argument.
	 * 
	 * @param after A 2nd {@link ThrowingConsumer} to run the argument on after this one
	 * @return The {@link ThrowingConsumer} made from composing this one and the given one
	 */
	default ThrowingConsumer<A, T> andThen(ThrowingConsumer<? super A, ? extends T> after){
		return a -> {
			this.accept(a);
			after.accept(a);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer} that runs this {@link ThrowingConsumer} and then also runs the
	 * given {@link Consumer} on the same argument.
	 *
	 * @param after A {@link Consumer} to run the argument on after this one
	 * @return The {@link ThrowingConsumer} made from composing this one and the given {@link Consumer}
	 */
	default ThrowingConsumer<A, T> andThen(Consumer<? super A> after){
		return a-> {
			this.accept(a);
			after.accept(a);
		};
	}
}
