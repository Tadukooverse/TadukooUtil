package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes three arguments and returns a result.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer3<A, B, C>{
	
	/**
	 * Takes three arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 */
	void accept(A a, B b, C c);
	
	/**
	 * Creates a {@link Consumer3} that runs this {@link Consumer3} and then also runs the
	 * given {@link Consumer3} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer3} to run the arguments on after this one
	 * @return The {@link Consumer3} made from composing this one and the given one
	 */
	default Consumer3<A, B, C> andThen(Consumer3<? super A, ? super B, ? super C> after){
		return (a, b, c) -> {
			this.accept(a, b, c);
			after.accept(a, b, c);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer3} that runs this {@link Consumer3} and then also runs the
	 * given {@link ThrowingConsumer3} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer3} to run the arguments on after this {@link Consumer3}
	 * @param <T> A {@link Throwable} that the {@link ThrowingConsumer3} is throwing
	 * @return The {@link ThrowingConsumer3} made from composing this {@link Consumer3} and the given {@link ThrowingConsumer3}
	 */
	default <T extends Throwable> ThrowingConsumer3<A, B, C, T> andThen(
			ThrowingConsumer3<? super A, ? super B, ? super C, ? extends T> after){
		return (a, b, c) -> {
			this.accept(a, b, c);
			after.accept(a, b, c);
		};
	}
}
