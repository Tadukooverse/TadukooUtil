package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes four arguments, returns a result,
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <D> The 4th input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer4<A, B, C, D, T extends Throwable>{
	
	/**
	 * Takes four arguments and consumes them.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @param d The 4th argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c, D d) throws T;
	
	/**
	 * Creates a ThrowingConsumer4 that runs this ThrowingConsumer4 and then also runs the
	 * given ThrowingConsumer4 on the same arguments.
	 * 
	 * @param after A 2nd ThrowingConsumer4 to run the arguments on after this one
	 * @return The ThrowingConsumer4 made from composing this one and the given one
	 */
	default ThrowingConsumer4<A, B, C, D, T> andThen(
			ThrowingConsumer4<? super A, ? super B, ? super C, ? super D, ? extends T> after){
		return (a, b, c, d) -> {
								this.accept(a, b, c, d);
								after.accept(a, b, c, d);
							};
	}
}
