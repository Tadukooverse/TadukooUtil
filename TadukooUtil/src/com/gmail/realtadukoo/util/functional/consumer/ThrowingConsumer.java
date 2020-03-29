package com.gmail.realtadukoo.util.functional.consumer;

import java.util.function.Consumer;

/**
 * A better version of Java's {@link Consumer} interface that 
 * allows for the consumers to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing consumers. 
 * See {@link ExceptionConsumer} for an example of a 
 * more fine-tuned extension.
 *
 * @param <S> The input argument type to be consumed
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingConsumer<S>{
	
	/**
	 * Takes a single argument and consumes it.
	 * 
	 * @param s The argument
	 * @throws Throwable Determined by the consumer, not required
	 */
	void accept(S s) throws Throwable;
	
	/**
	 * Creates a ThrowingConsumer that runs this ThrowingConsumer and then also runs the 
	 * given ThrowingConsumer on the same argument.
	 * 
	 * @param after A 2nd ThrowingConsumer to run the argument on after this one
	 * @return The ThrowingConsumer made from composing this one and the given one
	 */
	default ThrowingConsumer<S> andThen(ThrowingConsumer<? super S> after){
		return s -> {
						this.accept(s);
						after.accept(s);
					};
	}
}
