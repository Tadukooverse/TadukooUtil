package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.supplier.ThrowingSupplier;

/**
 * Represents a {@link ThrowingSupplier} that supplies a double
 *
 * @param <T> A {@link Throwable} that the supplier throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoubleSupplier<T extends Throwable> extends ThrowingSupplier<Double, T>{
}
