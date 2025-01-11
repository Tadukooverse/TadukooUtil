package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes four arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 * 
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer4<A, B, C, D, T extends Throwable>{
	
	/**
	 * Takes four arguments and consumes them.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d) throws T;
	
	/**
	 * Creates a {@link ThrowingConsumer4} that runs this {@link ThrowingConsumer4} and then also runs the
	 * given {@link ThrowingConsumer4} on the same arguments.
	 * 
	 * @param after A 2nd {@link ThrowingConsumer4} to run the arguments on after this one
	 * @return The {@link ThrowingConsumer4} made from composing this one and the given one
	 */
	default ThrowingConsumer4<A, B, C, D, T> andThen(
			ThrowingConsumer4<? super A, ? super B, ? super C, ? super D, ? extends T> after){
		return (a, b, c, d) -> {
			this.accept(a, b, c, d);
			after.accept(a, b, c, d);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer4} that runs this {@link ThrowingConsumer4} and then also runs the
	 * given {@link Consumer4} on the same arguments.
	 *
	 * @param after A {@link Consumer4} to run the arguments on after this {@link ThrowingConsumer4}
	 * @return The {@link ThrowingConsumer4} made from composing this {@link ThrowingConsumer4} and the given {@link Consumer4}
	 */
	default ThrowingConsumer4<A, B, C, D, T> andThen(Consumer4<? super A, ? super B, ? super C, ? super D> after){
		return (a, b, c, d) -> {
			this.accept(a, b, c, d);
			after.accept(a, b, c, d);
		};
	}
}
