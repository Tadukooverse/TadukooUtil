package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;

/**
 * Represents a {@link ThrowingConsumer} that consumes a double
 *
 * @param <T> A {@link Throwable} that the consumer throws
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoubleConsumer<T extends Throwable> extends ThrowingConsumer<Double, T>{
}
