package com.github.tadukoo.util.functional.supplier;

import java.util.function.Supplier;

/**
 * A better version of Java's {@link Supplier} interface that 
 * allows for the suppliers to throw whatever {@link Throwable}
 * is specified.
 *
 * @param <R> The output result type to be supplied
 * @param <T> The type of {@link Throwable} thrown by the supplier
 * 
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
@FunctionalInterface
public interface ThrowingSupplier<R, T extends Throwable>{
	
	/**
	 * Returns a result.
	 * 
	 * @return A result
	 * @throws T Determined by the supplier, not required
	 */
	R get() throws T;
}
