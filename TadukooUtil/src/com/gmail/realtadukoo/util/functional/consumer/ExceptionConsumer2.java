package com.gmail.realtadukoo.util.functional.consumer;

/**
 * A Consumer that takes two arguments and may throw 
 * an {@link Exception}. 
 * This is an extension of {@link ThrowingConsumer2} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The 1st input argument type to be consumed
 * @param <T> The 2nd input argument type to be consumed
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionConsumer2<S, T> extends ThrowingConsumer2<S, T>{
	
	/**
	 * Takes two arguments and consumes them.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @throws Exception Determined by the consumer, not required
	 */
	@Override
	void accept(S s, T t) throws Exception;
	
	/**
	 * Creates an ExceptionConsumer2 that runs this ExceptionConsumer2 and then also runs the 
	 * given ExceptionConsumer2 on the same arguments.
	 * 
	 * @param after A 2nd ExceptionConsumer2 to run the arguments on after this one
	 * @return The ExceptionConsumer2 made from composing this one and the given one
	 */
	default ExceptionConsumer2<S, T> andThen(ExceptionConsumer2<? super S, ? super T> after){
		return (s, t) -> {
							this.accept(s, t);
							after.accept(s, t);
						};
	}
}
