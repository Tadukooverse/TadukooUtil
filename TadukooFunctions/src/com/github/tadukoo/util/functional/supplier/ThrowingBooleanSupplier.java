package com.github.tadukoo.util.functional.supplier;

/**
 * A {@link ThrowingSupplier} that supplies a boolean
 *
 * @param <T> The type of {@link Throwable} thrown by the boolean supplier
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingBooleanSupplier<T extends Throwable> extends ThrowingSupplier<Boolean, T>{
}
