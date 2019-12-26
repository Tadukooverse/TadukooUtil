package com.gmail.realtadukoo.util.functional.supplier;

import java.util.function.Supplier;

/**
 * A better version of Java's {@link Supplier} interface that 
 * allows for the suppliers to throw anything. Using this requires 
 * you to check whatever may be thrown, but this class can be 
 * extended to allow for more specific throwing suppliers. 
 * See {@link ExceptionSupplier} for an example of a 
 * more fine-tuned extension.
 *
 * @param <R> The output result type to be supplied
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingSupplier<R>{
	
	/**
	 * Returns a result.
	 * 
	 * @return A result
	 * @throws Throwable
	 */
	public abstract R get() throws Throwable;
}
