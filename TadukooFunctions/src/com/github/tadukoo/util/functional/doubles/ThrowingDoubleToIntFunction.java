package com.github.tadukoo.util.functional.doubles;

import com.github.tadukoo.util.functional.function.ThrowingFunction;

/**
 * Represents a {@link ThrowingFunction} that takes a double and returns an int
 *
 * @param <T> A {@link Throwable} that is thrown by the function
 * @author Logan Ferree (Tadukoo)
 * @version Beta v.0.7
 */
@FunctionalInterface
public interface ThrowingDoubleToIntFunction<T extends Throwable> extends ThrowingFunction<Double, Integer, T>{
}
