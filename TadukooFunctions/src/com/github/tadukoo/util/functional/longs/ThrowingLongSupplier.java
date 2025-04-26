package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;

/**
 * Represents a {@link ThrowingSupplier} that supplies a long
 *
 * @param <T> A {@link Throwable} that the supplier throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongSupplier<T extends Throwable> extends ThrowingSupplier<Long, T>{
}
