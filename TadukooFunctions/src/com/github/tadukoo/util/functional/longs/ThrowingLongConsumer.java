package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer;

/**
 * Represents a {@link ThrowingConsumer} that consumes a long
 *
 * @param <T> The {@link Throwable} that is thrown by the consumer
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongConsumer<T extends Throwable> extends ThrowingConsumer<Long, T>{
}
