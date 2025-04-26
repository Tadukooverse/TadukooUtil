package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;

/**
 * Represents a {@link ThrowingSupplier} that supplies an int
 *
 * @param <T> A {@link Throwable} that the supplier throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntSupplier<T extends Throwable> extends ThrowingSupplier<Integer, T>{
}
