package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;

/**
 * Represents a {@link ThrowingConsumer} that consumes an int
 *
 * @param <T> The {@link Throwable} thrown by the consumer
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntConsumer<T extends Throwable> extends ThrowingConsumer<Integer, T>{
}
