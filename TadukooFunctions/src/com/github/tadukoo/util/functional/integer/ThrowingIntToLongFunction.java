package com.github.tadukoo.util.functional.integer;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes an int and returns a long
 *
 * @param <T> A {@link Throwable} that is thrown by the function
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingIntToLongFunction<T extends Throwable> extends ThrowingFunction<Integer, Long, T>{
}
