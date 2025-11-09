package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes six arguments and returns a result.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <E> The 5th input argument type to be consumed
 * @param <F> The 6th input argument type to be consumed
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Consumer6<A, B, C, D, E, F>{
	
	/**
	 * Takes six arguments and consumes them.
	 *
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @param e The 5th argument
	 * @param f The 6th argument
	 */
	void accept(A a, B b, C c, D d, E e, F f);
	
	/**
	 * Creates a {@link Consumer6} that runs this {@link Consumer6} and then also runs the
	 * given {@link Consumer6} on the same arguments.
	 *
	 * @param after A 2nd {@link Consumer6} to run the arguments on after this one
	 * @return The {@link Consumer6} made from composing this one and the given one
	 */
	default Consumer6<A, B, C, D, E, F> andThen(
			Consumer6<? super A, ? super B, ? super C, ? super D, ? super E, ? super F> after){
		return (a, b, c, d, e, f) -> {
			this.accept(a, b, c, d, e, f);
			after.accept(a, b, c, d, e, f);
		};
	}
	
	/**
	 * Creates a {@link ThrowingConsumer6} that runs this {@link Consumer6} and then also runs the
	 * given {@link ThrowingConsumer6} on the same arguments.
	 *
	 * @param after A {@link ThrowingConsumer6} to run the arguments on after this {@link Consumer6}
	 * @param <T> A {@link Throwable} that the {@link ThrowingConsumer6} is throwing
	 * @return The {@link ThrowingConsumer6} made from composing this {@link Consumer6} and the given {@link ThrowingConsumer6}
	 */
	default <T extends Throwable> ThrowingConsumer6<A, B, C, D, E, F, T> andThen(
			ThrowingConsumer6<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? extends T> after){
		return (a, b, c, d, e, f) -> {
			this.accept(a, b, c, d, e, f);
			after.accept(a, b, c, d, e, f);
		};
	}
}
