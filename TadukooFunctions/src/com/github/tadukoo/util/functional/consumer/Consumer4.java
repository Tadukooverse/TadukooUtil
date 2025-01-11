package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes four arguments and returns a result.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer4<A, B, C, D>{
	
	/**
	 * Takes four arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 */
	void accept(A a, B b, C c, D d);
	
	/**
	 * Creates a {@link Consumer4} that runs this {@link Consumer4} and then also runs the
	 * given {@link Consumer4} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer4} to run the arguments on after this one
	 * @return The {@link Consumer4} made from composing this one and the given one
	 */
	default Consumer4<A, B, C, D> andThen(Consumer4<? super A, ? super B, ? super C, ? super D> after){
		return (a, b, c, d) -> {
			this.accept(a, b, c, d);
			after.accept(a, b, c, d);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer4} that runs this {@link Consumer4} and then also runs the
	 * given {@link ThrowingConsumer4} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer4} to run the arguments on after this {@link Consumer4}
	 * @return The {@link ThrowingConsumer4} made from composing this {@link Consumer4} and the given {@link ThrowingConsumer4}
	 */
	default <T extends Throwable> ThrowingConsumer4<A, B, C, D, T> andThen(
			ThrowingConsumer4<? super A, ? super B, ? super C, ? super D, ? extends T> after){
		return (a, b, c, d) -> {
			this.accept(a, b, c, d);
			after.accept(a, b, c, d);
		};
	}
}
