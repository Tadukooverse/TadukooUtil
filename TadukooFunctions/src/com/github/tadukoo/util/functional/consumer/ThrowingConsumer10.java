package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes ten arguments, returns a result,
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
 * @param <J> The 10th input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3
 */
@FunctionalInterface
public interface ThrowingConsumer10<A, B, C, D, E, F, G, H, I, J, T extends Throwable>{
	
	/**
	 * Takes ten arguments and consumes them.
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
	 * @param j The 10th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) throws T;
	
	/**
	 * Creates a ThrowingConsumer10 that runs this ThrowingConsumer10 and then also runs the
	 * given ThrowingConsumer10 on the same arguments.
	 *
	 * @param after A 2nd ThrowingConsumer10 to run the arguments on after this one
	 * @return The ThrowingConsumer10 made from composing this one and the given one
	 */
	default ThrowingConsumer10<A, B, C, D, E, F, G, H, I, J, T> andThen(
			ThrowingConsumer10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I, ? super J, ? extends T> after){
		return (a, b, c, d, e, f, g, h, i, j) -> {
								this.accept(a, b, c, d, e, f, g, h, i, j);
								after.accept(a, b, c, d, e, f, g, h, i, j);
							};
	}
}
