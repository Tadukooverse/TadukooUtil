package com.github.tadukoo.util.functional.supplier;

/**
 * A better version of Java's {@link java.util.function.Supplier} interface that
 * is more compatible with {@link ThrowingSupplier} / other Throwing interfaces
 * that allow for throwing exceptions.
 *
 * @param <R> The output result type to be supplied
 *
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface Supplier<R>{
	
	/**
	 * Returns a result.
	 *
	 * @return A result
	 */
	R get();
}
