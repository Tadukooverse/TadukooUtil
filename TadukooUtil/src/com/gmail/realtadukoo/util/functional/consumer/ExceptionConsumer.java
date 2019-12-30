package com.gmail.realtadukoo.util.functional.consumer;

/**
 * A Consumer that takes a single argument and may throw 
 * an {@link Exception}. 
 * This is an extension of {@link ThrowingConsumer} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <S> The input argument type to be consumed
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionConsumer<S> extends ThrowingConsumer<S>{
	
	/**
	 * Takes a single argument and consumes it.
	 * 
	 * @param s The argument
	 * @throws Exception
	 */
	@Override
	public abstract void accept(S s) throws Exception;
	
	/**
	 * Creates an ExceptionConsumer that runs this ExceptionConsumer and then also runs the 
	 * given ExceptionConsumer on the same argument.
	 * 
	 * @param after A 2nd ExceptionConsumer to run the argument on after this one
	 * @return The ExceptionConsumer made from composing this one and the given one
	 */
	public default ExceptionConsumer<S> andThen(ExceptionConsumer<? super S> after){
		return s -> {
						this.accept(s);
						after.accept(s);
					};
	}
}
