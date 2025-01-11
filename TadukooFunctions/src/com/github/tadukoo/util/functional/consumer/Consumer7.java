package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes seven arguments and returns a result.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <E> The 5th input argument type to be consumed
 * @param <F> The 6th input argument type to be consumed
 * @param <G> The 7th input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer7<A, B, C, D, E, F, G>{
	
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
	 */
	void accept(A a, B b, C c, D d, E e, F f, G g);
	
	/**
	 * Creates a {@link Consumer7} that runs this {@link Consumer7} and then also runs the
	 * given {@link Consumer7} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer7} to run the arguments on after this one
	 * @return The {@link Consumer7} made from composing this one and the given one
	 */
	default Consumer7<A, B, C, D, E, F, G> andThen(
			Consumer7<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G> after){
		return (a, b, c, d, e, f, g) -> {
			this.accept(a, b, c, d, e, f, g);
			after.accept(a, b, c, d, e, f, g);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer7} that runs this {@link Consumer7} and then also runs the
	 * given {@link ThrowingConsumer7} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer7} to run the arguments on after this {@link Consumer7}
	 * @return The {@link ThrowingConsumer7} made from composing this {@link Consumer7} and the given {@link ThrowingConsumer7}
	 */
	default <T extends Throwable> ThrowingConsumer7<A, B, C, D, E, F, G, T> andThen(
			ThrowingConsumer7<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G,
					? extends T> after){
		return (a, b, c, d, e, f, g) -> {
			this.accept(a, b, c, d, e, f, g);
			after.accept(a, b, c, d, e, f, g);
		};
	}
}
