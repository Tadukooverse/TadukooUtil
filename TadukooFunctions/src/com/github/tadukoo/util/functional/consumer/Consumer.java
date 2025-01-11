package com.github.tadukoo.util.functional.consumer;

/**
 * A better version of Java's {@link java.util.function.Consumer} interface that
 * is more compatible with {@link ThrowingConsumer} / other throwing interfaces
 *
 * @param <A> The input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer<A>{
	
	/**
	 * Takes a single argument and consumes it.
	 *
	 * @param a The argument
	 */
	void accept(A a);
	
	/**
	 * Creates a {@link Consumer} that runs this {@link Consumer} and then also runs the
	 * given {@link Consumer} on the same argument.
	 *
	 * @param after A 2nd {@link Consumer} to run the argument on after this one
	 * @return The {@link Consumer} made from composing this one and the given {@link Consumer}
	 */
	default Consumer<A> andThen(Consumer<? super A> after){
		return a-> {
			this.accept(a);
			after.accept(a);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer} that runs this {@link Consumer} and then also runs the
	 * given {@link ThrowingConsumer} on the same argument.
	 *
	 * @param after A {@link ThrowingConsumer} to run the argument on after this {@link Consumer}
	 * @param <T> A {@link Throwable} that the {@link ThrowingConsumer} is throwing
	 * @return The {@link ThrowingConsumer} made from composing this {@link Consumer} and the given {@link ThrowingConsumer}
	 */
	default <T extends Throwable> ThrowingConsumer<A, T> andThen(ThrowingConsumer<? super A, ? extends T> after){
		return a -> {
			this.accept(a);
			after.accept(a);
		};
	}
}
