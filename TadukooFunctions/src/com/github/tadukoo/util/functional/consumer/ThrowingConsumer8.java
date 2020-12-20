package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes eight arguments, returns a result,
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
 * @param <T> The type of {@link Throwable} thrown by the consumer
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingConsumer8<A, B, C, D, E, F, G, H, T extends Throwable>{
	
	/**
	 * Takes eight arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 * @param g The 7th argument
	 * @param h The 8th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g, H h) throws T;
	
	/**
	 * Creates a ThrowingConsumer8 that runs this ThrowingConsumer8 and then also runs the
	 * given ThrowingConsumer8 on the same arguments.
	 *
	 * @param after A 2nd ThrowingConsumer8 to run the arguments on after this one
	 * @return The ThrowingConsumer8 made from composing this one and the given one
	 */
	default ThrowingConsumer8<A, B, C, D, E, F, G, H, T> andThen(
			ThrowingConsumer8<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? extends T> after){
		return (a, b, c, d, e, f, g, h) -> {
								this.accept(a, b, c, d, e, f, g, h);
								after.accept(a, b, c, d, e, f, g, h);
							};
	}
}
