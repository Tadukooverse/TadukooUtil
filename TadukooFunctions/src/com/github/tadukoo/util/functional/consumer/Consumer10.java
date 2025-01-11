package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes ten arguments and returns a result.
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
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer10<A, B, C, D, E, F, G, H, I, J>{
	
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
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j);
	
	/**
	 * Creates a {@link Consumer10} that runs this {@link Consumer10} and then also runs the
	 * given {@link Consumer10} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer10} to run the arguments on after this one
	 * @return The {@link Consumer10} made from composing this one and the given one
	 */
	default Consumer10<A, B, C, D, E, F, G, H, I, J> andThen(
			Consumer10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I, ? super J> after){
		return (a, b, c, d, e, f, g, h, i, j) -> {
			this.accept(a, b, c, d, e, f, g, h, i, j);
			after.accept(a, b, c, d, e, f, g, h, i, j);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer10} that runs this {@link Consumer10} and then also runs the
	 * given {@link ThrowingConsumer10} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer10} to run the arguments on after this {@link Consumer10}
	 * @param <T> A {@link Throwable} that the {@link ThrowingConsumer10} is throwing
	 * @return The {@link ThrowingConsumer10} made from composing this {@link Consumer10} and the given {@link ThrowingConsumer10}
	 */
	default <T extends Throwable> ThrowingConsumer10<A, B, C, D, E, F, G, H, I, J, T> andThen(
			ThrowingConsumer10<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I, ? super J, ? extends T> after){
		return (a, b, c, d, e, f, g, h, i, j) -> {
			this.accept(a, b, c, d, e, f, g, h, i, j);
			after.accept(a, b, c, d, e, f, g, h, i, j);
		};
	}
}
