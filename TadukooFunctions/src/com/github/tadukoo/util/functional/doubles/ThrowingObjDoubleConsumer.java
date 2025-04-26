package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.consumer.ThrowingConsumer2;

/**
 * Represents a {@link ThrowingConsumer2} that consumes something and a double
 *
 * @param <A> The 1st input type
 * @param <T> The {@link Throwable} thrown by the consumer
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingObjDoubleConsumer<A, T extends Throwable> extends ThrowingConsumer2<A, Double, T>{
}
