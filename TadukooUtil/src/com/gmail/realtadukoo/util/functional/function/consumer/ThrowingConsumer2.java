package com.gmail.realtadukoo.util.functional.function.consumer;

import java.util.function.BiConsumer;

/**
 * A better version of Java's {@link BiConsumer} interface that 
 * allows for the consumers to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing suppliers. 
 * See {@link ExceptionConsumer} for an example of a 
 * more fine-tuned extension.
 *
 * @param <S> The 1st input argument type to be consumed
 * @param <T> The 2nd input argument type to be consumed
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer2<S, T>{
	
	/**
	 * Takes two arguments and consumes them.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @throws Throwable
	 */
	public abstract void accept(S s, T t) throws Throwable;
	
	/**
	 * Creates a ThrowingConsumer2 that runs this ThrowingConsumer2 and then also runs the 
	 * given ThrowingConsumer2 on the same arguments.
	 * 
	 * @param after A 2nd ThrowingConsumer2 to run the arguments on after this one
	 * @return The ThrowingConsumer2 made from composing this one and the given one
	 */
	public default ThrowingConsumer2<S, T> andThen(ThrowingConsumer2<? super S, ? super T> after){
		return (s, t) -> {
							this.accept(s, t);
							after.accept(s, t);
						};
	}
}
