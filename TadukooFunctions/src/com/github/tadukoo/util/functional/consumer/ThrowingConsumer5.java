package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes five arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <E> The 5th input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer5<A, B, C, D, E, T extends Throwable>{
	
	/**
	 * Takes five arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d, E e) throws T;
	
	/**
	 * Creates a {@link ThrowingConsumer5} that runs this {@link ThrowingConsumer5} and then also runs the
	 * given {@link ThrowingConsumer5} on the same arguments.
	 * 
	 * @param after A 2nd {@link ThrowingConsumer5} to run the arguments on after this one
	 * @return The {@link ThrowingConsumer5} made from composing this one and the given one
	 */
	default ThrowingConsumer5<A, B, C, D, E, T> andThen(
			ThrowingConsumer5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends T> after){
		return (a, b, c, d, e) -> {
			this.accept(a, b, c, d, e);
			after.accept(a, b, c, d, e);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer5} that runs this {@link ThrowingConsumer5} and then also runs the
	 * given {@link Consumer5} on the same arguments.
	 *
	 * @param after A {@link Consumer5} to run the arguments on after this {@link ThrowingConsumer5}
	 * @return The {@link ThrowingConsumer5} made from composing this {@link ThrowingConsumer5} and the given {@link Consumer5}
	 */
	default ThrowingConsumer5<A, B, C, D, E, T> andThen(
			Consumer5<? super A, ? super B, ? super C, ? super D, ? super E> after){
		return (a, b, c, d, e) -> {
			this.accept(a, b, c, d, e);
			after.accept(a, b, c, d, e);
		};
	}
}
