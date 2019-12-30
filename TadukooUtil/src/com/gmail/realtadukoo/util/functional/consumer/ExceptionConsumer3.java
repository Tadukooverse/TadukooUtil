package com.gmail.realtadukoo.util.functional.consumer;

/**
 * A Consumer that takes three arguments and may throw 
 * an {@link Exception}. 
 * This is an extension of {@link ThrowingConsumer3} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The 1st input argument type to be consumed
 * @param <T> The 2nd input argument type to be consumed
 * @param <U> The 3rd input argument type to be consumed
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionConsumer3<S, T, U> extends ThrowingConsumer3<S, T, U>{
	
	/**
	 * Takes two arguments and consumes them.
	 * 
	 * @param s The 1st argument
	 * @param t The 2nd argument
	 * @param u The 3rd argument
	 * @throws Exception
	 */
	@Override
	public abstract void accept(S s, T t, U u) throws Exception;
	
	/**
	 * Creates an ExceptionConsumer3 that runs this ExceptionConsumer3 and then also runs the 
	 * given ExceptionConsumer3 on the same arguments.
	 * 
	 * @param after A 2nd ExceptionConsumer3 to run the arguments on after this one
	 * @return The ExceptionConsumer3 made from composing this one and the given one
	 */
	public default ExceptionConsumer3<S, T, U> andThen(ExceptionConsumer3<? super S, ? super T, ? super U> after){
		return (s, t, u) -> {
							this.accept(s, t, u);
							after.accept(s, t, u);
						};
	}
}
