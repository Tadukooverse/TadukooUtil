package com.github.tadukoo.util.functional.longs;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes a long and returns an int
 *
 * @param <T> A {@link Throwable} that is thrown by the function
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingLongToIntFunction<T extends Throwable> extends ThrowingFunction<Long, Integer, T>{
}
