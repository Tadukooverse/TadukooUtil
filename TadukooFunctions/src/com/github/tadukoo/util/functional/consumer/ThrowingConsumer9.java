package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes nine arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <E> The 5th input argument type to be consumed
 * @param <F> The 6th input argument type to be consumed
 * @param <G> The 7th input argument type to be consumed
 * @param <H> The 8th input argument type to be consumed
 * @param <I> The 9th input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 * @since Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingConsumer9<A, B, C, D, E, F, G, H, I, T extends Throwable>{
	
	/**
	 * Takes nine arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @param h The 8th argument
	 * @param i The 9th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g, H h, I i) throws T;
	
	/**
	 * Creates a {@link ThrowingConsumer9} that runs this {@link ThrowingConsumer9} and then also runs the
	 * given {@link ThrowingConsumer9} on the same arguments.
	 *
	 * @param after A 2nd {@link ThrowingConsumer9} to run the arguments on after this one
	 * @return The {@link ThrowingConsumer9} made from composing this one and the given one
	 */
	default ThrowingConsumer9<A, B, C, D, E, F, G, H, I, T> andThen(
			ThrowingConsumer9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I, ? extends T> after){
		return (a, b, c, d, e, f, g, h, i) -> {
			this.accept(a, b, c, d, e, f, g, h, i);
			after.accept(a, b, c, d, e, f, g, h, i);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer9} that runs this {@link ThrowingConsumer9} and then also runs the
	 * given {@link Consumer9} on the same arguments.
	 *
	 * @param after A {@link Consumer9} to run the arguments on after this {@link ThrowingConsumer9}
	 * @return The {@link ThrowingConsumer9} made from composing this {@link ThrowingConsumer9} and the given {@link Consumer9}
	 */
	default ThrowingConsumer9<A, B, C, D, E, F, G, H, I, T> andThen(
			Consumer9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I> after){
		return (a, b, c, d, e, f, g, h, i) -> {
			this.accept(a, b, c, d, e, f, g, h, i);
			after.accept(a, b, c, d, e, f, g, h, i);
		};
	}
}
