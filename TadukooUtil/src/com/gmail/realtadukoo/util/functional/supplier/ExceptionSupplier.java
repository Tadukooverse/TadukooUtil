package com.gmail.realtadukoo.util.functional.supplier;

/**
 * A Supplier that returns a result and may throw an {@link Exception}. 
 * This is an extension of {@link ThrowingSupplier} 
 * that only allows for throwing an {@link Exception}.
 * 
 * @param <R> The output result type to be supplied
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ExceptionSupplier<R> extends ThrowingSupplier<R>{
	
	/**
	 * Returns a result.
	 * 
	 * @return A result
	 * @throws Exception Determined by the supplier, not required
	 */
	@Override
	R get() throws Exception;
}
