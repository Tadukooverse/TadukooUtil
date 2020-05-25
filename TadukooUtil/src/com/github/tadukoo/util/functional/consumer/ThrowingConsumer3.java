package com.github.tadukoo.util.functional.consumer;

/**
 * A consumer that takes three arguments, returns a result, 
 * and may throw a {@link Throwable}.
 *
 * @param <A> The 1st input argument type to be consumed
 * @param <B> The 2nd input argument type to be consumed
 * @param <C> The 3rd input argument type to be consumed
 * @param <T> The type of {@link Throwable} thrown by the consumer
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer3<A, B, C, T extends Throwable>{
	
	/**
	 * Takes three arguments and consumes them.
	 * 
	 * @param a The 1st argument
	 * @param b The 2nd argument
	 * @param c The 3rd argument
	 * @throws T Determined by the consumer, not required
	 */
	void accept(A a, B b, C c) throws T;
	
	/**
	 * Creates a ThrowingConsumer3 that runs this ThrowingConsumer3 and then also runs the 
	 * given ThrowingConsumer3 on the same arguments.
	 * 
	 * @param after A 2nd ThrowingConsumer3 to run the arguments on after this one
	 * @return The ThrowingConsumer3 made from composing this one and the given one
	 */
	default ThrowingConsumer3<A, B, C, T> andThen(ThrowingConsumer3<? super A, ? super B, ? super C, ? extends T> after){
		return (a, b, c) -> {
								this.accept(a, b, c);
								after.accept(a, b, c);
							};
	}
}
