package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes seven arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <E> The 5th input argument type to be consumed
 * @param <F> The 6th input argument type to be consumed
 * @param <G> The 7th input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingConsumer7<A, B, C, D, E, F, G, T extends Throwable>{
	
	/**
	 * Takes seven arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g) throws T;
	
	/**
	 * Creates a {@link ThrowingConsumer7} that runs this {@link ThrowingConsumer7} and then also runs the
	 * given {@link ThrowingConsumer7} on the same arguments.
	 *
	 * @param after A 2nd {@link ThrowingConsumer7} to run the arguments on after this one
	 * @return The {@link ThrowingConsumer7} made from composing this one and the given one
	 */
	default ThrowingConsumer7<A, B, C, D, E, F, G, T> andThen(
			ThrowingConsumer7<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G, ? extends T> after){
		return (a, b, c, d, e, f, g) -> {
			this.accept(a, b, c, d, e, f, g);
			after.accept(a, b, c, d, e, f, g);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer7} that runs this {@link ThrowingConsumer7} and then also runs the
	 * given {@link Consumer7} on the same arguments.
	 *
	 * @param after A {@link Consumer7} to run the arguments on after this {@link ThrowingConsumer7}
	 * @return The {@link ThrowingConsumer7} made from composing this {@link ThrowingConsumer7} and the given {@link Consumer7}
	 */
	default ThrowingConsumer7<A, B, C, D, E, F, G, T> andThen(
			Consumer7<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G> after){
		return (a, b, c, d, e, f, g) -> {
			this.accept(a, b, c, d, e, f, g);
			after.accept(a, b, c, d, e, f, g);
		};
	}
}
