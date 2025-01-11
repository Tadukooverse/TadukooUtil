package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes five arguments and returns a result.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <E> The 5th input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer5<A, B, C, D, E>{
	
	/**
	 * Takes five arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 */
	void accept(A a, B b, C c, D d, E e);
	
	/**
	 * Creates a {@link Consumer5} that runs this {@link Consumer5} and then also runs the
	 * given {@link Consumer5} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer5} to run the arguments on after this one
	 * @return The {@link Consumer5} made from composing this one and the given one
	 */
	default Consumer5<A, B, C, D, E> andThen(Consumer5<? super A, ? super B, ? super C, ? super D, ? super E> after){
		return (a, b, c, d, e) -> {
			this.accept(a, b, c, d, e);
			after.accept(a, b, c, d, e);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer5} that runs this {@link Consumer5} and then also runs the
	 * given {@link ThrowingConsumer5} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer5} to run the arguments on after this {@link Consumer5}
	 * @param <T> A {@link Throwable} that the {@link ThrowingConsumer5} is throwing
	 * @return The {@link ThrowingConsumer5} made from composing this {@link Consumer5} and the given {@link ThrowingConsumer5}
	 */
	default <T extends Throwable> ThrowingConsumer5<A, B, C, D, E, T> andThen(
			ThrowingConsumer5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends T> after){
		return (a, b, c, d, e) -> {
			this.accept(a, b, c, d, e);
			after.accept(a, b, c, d, e);
		};
	}
}
