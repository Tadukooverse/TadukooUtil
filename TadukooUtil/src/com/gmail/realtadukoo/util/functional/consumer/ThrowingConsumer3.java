package com.gmail.realtadukoo.util.functional.consumer;

/**
 * A consumer that takes three arguments, returns a result, 
 * and may throw a {@link Throwable}.
 *
 * @param <S> The 1st input argument type to be consumed
 * @param <T> The 2nd input argument type to be consumed
 * @param <U> The 3rd input argument type to be consumed
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer3<S, T, U>{
	
	/**
	 * Takes three arguments and consumes them.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @param u The 3rd argument
	 * @throws Throwable
	 */
	public abstract void accept(S s, T t, U u) throws Throwable;
	
	/**
	 * Creates a ThrowingConsumer3 that runs this ThrowingConsumer3 and then also runs the 
	 * given ThrowingConsumer3 on the same arguments.
	 * 
	 * @param after A 2nd ThrowingConsumer3 to run the arguments on after this one
	 * @return The ThrowingConsumer3 made from composing this one and the given one
	 */
	public default ThrowingConsumer3<S, T, U> andThen(ThrowingConsumer3<? super S, ? super T, ? super U> after){
		return (s, t, u) -> {
								this.accept(s, t, u);
								after.accept(s, t, u);
							};
	}
}
