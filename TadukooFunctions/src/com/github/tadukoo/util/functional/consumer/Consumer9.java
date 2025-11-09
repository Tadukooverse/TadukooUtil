package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes nine arguments and returns a result.
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
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer9<A, B, C, D, E, F, G, H, I>{
	
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
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g, H h, I i);
	
	/**
	 * Creates a {@link Consumer9} that runs this {@link Consumer9} and then also runs the
	 * given {@link Consumer9} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer9} to run the arguments on after this one
	 * @return The {@link Consumer9} made from composing this one and the given one
	 */
	default Consumer9<A, B, C, D, E, F, G, H, I> andThen(
			Consumer9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I> after){
		return (a, b, c, d, e, f, g, h, i) -> {
			this.accept(a, b, c, d, e, f, g, h, i);
			after.accept(a, b, c, d, e, f, g, h, i);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer9} that runs this {@link Consumer9} and then also runs the
	 * given {@link ThrowingConsumer9} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer9} to run the arguments on after this {@link Consumer9}
	 * @param <T> A {@link Throwable} that the {@link ThrowingConsumer9} is throwing
	 * @return The {@link ThrowingConsumer9} made from composing this {@link Consumer9} and the given {@link ThrowingConsumer9}
	 */
	default <T extends Throwable> ThrowingConsumer9<A, B, C, D, E, F, G, H, I, T> andThen(
			ThrowingConsumer9<? super A, ? super B, ? super C, ? super D, ? super E,
					? super F, ? super G,? super H, ? super I, ? extends T> after){
		return (a, b, c, d, e, f, g, h, i) -> {
			this.accept(a, b, c, d, e, f, g, h, i);
			after.accept(a, b, c, d, e, f, g, h, i);
		};
	}
}
