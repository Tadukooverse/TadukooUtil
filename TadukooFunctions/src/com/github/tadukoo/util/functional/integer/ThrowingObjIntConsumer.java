package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;

/**
 * Represents a {@link ThrowingConsumer2} that consumes something and an int
 *
 * @param <A> The 1st input type
 * @param <T> The {@link Throwable} thrown by the consumer
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingObjIntConsumer<A, T extends Throwable> extends ThrowingConsumer2<A, Integer, T>{
}
